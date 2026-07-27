package com.fiap.cardioradar.domain.dataaccess;


import com.fiap.cardioradar.domain.RiscoMunicipal;

import java.time.YearMonth;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface RiscoMunicipalDataAccess {

    Optional<RiscoMunicipal>
    buscarPorMunicipioECompetencia(
            UUID municipioId,
            YearMonth competencia
    );

    Optional<RiscoMunicipal> buscarUltimoPorMunicipio(
            UUID municipioId
    );

    List<RiscoMunicipal> listarPorMunicipio(
            UUID municipioId
    );

    List<RiscoMunicipal> listarPorCompetencia(
            YearMonth competencia
    );

    void salvar(RiscoMunicipal risco);
}
