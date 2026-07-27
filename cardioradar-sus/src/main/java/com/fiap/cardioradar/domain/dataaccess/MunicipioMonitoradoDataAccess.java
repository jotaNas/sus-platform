package com.fiap.cardioradar.domain.dataaccess;

import com.fiap.cardioradar.domain.MunicipioMonitorado;

import java.util.Optional;

import java.util.UUID;


public interface MunicipioMonitoradoDataAccess {

    void salvar(MunicipioMonitorado municipio);

    Optional<MunicipioMonitorado> buscarPorId(UUID id);

    Optional<MunicipioMonitorado> buscarPorCodigoIbge(
            String codigoIbge
    );

    boolean existePorCodigoIbge(String codigoIbge);
}