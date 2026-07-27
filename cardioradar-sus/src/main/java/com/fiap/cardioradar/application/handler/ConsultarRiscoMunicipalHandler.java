package com.fiap.cardioradar.application.handler;


import com.fiap.cardioradar.application.query.ConsultarRiscoMunicipalQuery;
import com.fiap.cardioradar.domain.RiscoMunicipal;
import com.fiap.cardioradar.domain.dataaccess.RiscoMunicipalDataAccess;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class ConsultarRiscoMunicipalHandler {

    private final RiscoMunicipalDataAccess riscoMunicipalDataAccess;

    public ConsultarRiscoMunicipalHandler(
            RiscoMunicipalDataAccess riscoMunicipalDataAccess
    ) {
        this.riscoMunicipalDataAccess = riscoMunicipalDataAccess;
    }

    @Transactional(Transactional.TxType.SUPPORTS)
    public RiscoMunicipal handle(
            ConsultarRiscoMunicipalQuery query
    ) {
        validarQuery(query);

        return riscoMunicipalDataAccess
                .buscarPorMunicipioECompetencia(
                        query.municipioId(),
                        query.competencia()
                )
                .orElseThrow(() ->
                        new IllegalArgumentException(
                                "Risco municipal não encontrado para o município "
                                        + query.municipioId()
                                        + " na competência "
                                        + query.competencia()
                                        + "."
                        )
                );
    }

    private void validarQuery(
            ConsultarRiscoMunicipalQuery query
    ) {
        if (query == null) {
            throw new IllegalArgumentException(
                    "Os dados da consulta são obrigatórios."
            );
        }

        if (query.municipioId() == null) {
            throw new IllegalArgumentException(
                    "Município é obrigatório."
            );
        }

        if (query.competencia() == null) {
            throw new IllegalArgumentException(
                    "Competência é obrigatória."
            );
        }
    }
}
