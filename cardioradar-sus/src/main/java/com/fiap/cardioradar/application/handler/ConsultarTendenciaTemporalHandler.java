package com.fiap.cardioradar.application.handler;


import com.fiap.cardioradar.application.query.ConsultarTendenciaTemporalQuery;
import com.fiap.cardioradar.domain.RiscoMunicipal;
import com.fiap.cardioradar.domain.dataaccess.RiscoMunicipalDataAccess;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

import java.util.Comparator;
import java.util.List;

@ApplicationScoped
public class ConsultarTendenciaTemporalHandler {

    private final RiscoMunicipalDataAccess riscoMunicipalDataAccess;

    public ConsultarTendenciaTemporalHandler(
            RiscoMunicipalDataAccess riscoMunicipalDataAccess
    ) {
        this.riscoMunicipalDataAccess = riscoMunicipalDataAccess;
    }

    @Transactional(Transactional.TxType.SUPPORTS)
    public List<RiscoMunicipal> handle(
            ConsultarTendenciaTemporalQuery query
    ) {
        if (query == null || query.municipioId() == null) {
            throw new IllegalArgumentException(
                    "Município é obrigatório."
            );
        }

        return riscoMunicipalDataAccess
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
    }
}
