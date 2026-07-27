package com.fiap.cardioradar.application.handler;


import com.fiap.cardioradar.application.query.ConsultarRankingMunicipiosQuery;
import com.fiap.cardioradar.domain.RiscoMunicipal;
import com.fiap.cardioradar.domain.dataaccess.RiscoMunicipalDataAccess;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

import java.util.Comparator;
import java.util.List;

@ApplicationScoped
public class ConsultarRankingMunicipiosHandler {

    private static final int LIMITE_PADRAO = 10;
    private static final int LIMITE_MAXIMO = 100;

    private final RiscoMunicipalDataAccess riscoMunicipalDataAccess;

    public ConsultarRankingMunicipiosHandler(
            RiscoMunicipalDataAccess riscoMunicipalDataAccess
    ) {
        this.riscoMunicipalDataAccess = riscoMunicipalDataAccess;
    }

    @Transactional(Transactional.TxType.SUPPORTS)
    public List<RiscoMunicipal> handle(
            ConsultarRankingMunicipiosQuery query
    ) {
        validar(query);

        int limite = normalizarLimite(query.limite());

        return riscoMunicipalDataAccess
                .listarPorCompetencia(query.competencia())
                .stream()
                .sorted(
                        Comparator.comparingDouble(
                                RiscoMunicipal::getIndicePressaoCardiovascular
                        ).reversed()
                )
                .limit(limite)
                .toList();
    }

    private void validar(
            ConsultarRankingMunicipiosQuery query
    ) {
        if (query == null) {
            throw new IllegalArgumentException(
                    "Os dados da consulta do ranking são obrigatórios."
            );
        }

        if (query.competencia() == null) {
            throw new IllegalArgumentException(
                    "A competência é obrigatória."
            );
        }

        if (query.limite() < 0) {
            throw new IllegalArgumentException(
                    "O limite não pode ser negativo."
            );
        }

        if (query.limite() > LIMITE_MAXIMO) {
            throw new IllegalArgumentException(
                    "O limite máximo permitido é "
                            + LIMITE_MAXIMO
                            + "."
            );
        }
    }

    private int normalizarLimite(
            int limite
    ) {
        return limite == 0
                ? LIMITE_PADRAO
                : limite;
    }
}
