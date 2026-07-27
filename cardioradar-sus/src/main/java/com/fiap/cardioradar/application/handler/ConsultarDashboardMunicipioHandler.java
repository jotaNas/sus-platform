package com.fiap.cardioradar.application.handler;


import com.fiap.cardioradar.application.query.ConsultarDashboardMunicipioQuery;
import com.fiap.cardioradar.application.result.DashboardMunicipioResult;
import com.fiap.cardioradar.domain.RiscoMunicipal;
import com.fiap.cardioradar.domain.dataaccess.RiscoMunicipalDataAccess;
import com.fiap.cardioradar.domain.vo.NivelRisco;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

import java.util.Comparator;
import java.util.List;

@ApplicationScoped
public class ConsultarDashboardMunicipioHandler {

    private final RiscoMunicipalDataAccess riscoMunicipalDataAccess;

    public ConsultarDashboardMunicipioHandler(
            RiscoMunicipalDataAccess riscoMunicipalDataAccess
    ) {
        this.riscoMunicipalDataAccess = riscoMunicipalDataAccess;
    }

    @Transactional(Transactional.TxType.SUPPORTS)
    public DashboardMunicipioResult handle(
            ConsultarDashboardMunicipioQuery query
    ) {
        validar(query);

        List<RiscoMunicipal> historico =
                riscoMunicipalDataAccess
                        .listarPorMunicipio(
                                query.municipioId()
                        )
                        .stream()
                        .sorted(
                                Comparator.comparing(
                                        RiscoMunicipal::getCompetencia
                                )
                        )
                        .toList();

        if (historico.isEmpty()) {
            throw new IllegalArgumentException(
                    "Nenhum risco foi encontrado para o município "
                            + query.municipioId()
                            + "."
            );
        }

        RiscoMunicipal riscoAtual =
                historico.get(historico.size() - 1);

        long quantidadePeriodosCriticos =
                historico.stream()
                        .filter(this::isCritico)
                        .count();

        double menorIndice =
                historico.stream()
                        .mapToDouble(
                                RiscoMunicipal::getIndicePressaoCardiovascular
                        )
                        .min()
                        .orElse(0.0);

        double maiorIndice =
                historico.stream()
                        .mapToDouble(
                                RiscoMunicipal::getIndicePressaoCardiovascular
                        )
                        .max()
                        .orElse(0.0);

        double mediaIndice =
                historico.stream()
                        .mapToDouble(
                                RiscoMunicipal::getIndicePressaoCardiovascular
                        )
                        .average()
                        .orElse(0.0);

        return new DashboardMunicipioResult(
                query.municipioId(),
                riscoAtual,
                historico.size(),
                quantidadePeriodosCriticos,
                menorIndice,
                maiorIndice,
                mediaIndice,
                historico
        );
    }

    private boolean isCritico(
            RiscoMunicipal risco
    ) {
        return risco.getNivel() == NivelRisco.CRITICO;
    }

    private void validar(
            ConsultarDashboardMunicipioQuery query
    ) {
        if (query == null) {
            throw new IllegalArgumentException(
                    "Os dados da consulta do dashboard são obrigatórios."
            );
        }

        if (query.municipioId() == null) {
            throw new IllegalArgumentException(
                    "O município é obrigatório."
            );
        }
    }
}
