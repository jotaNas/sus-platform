package com.fiap.cardioradar.domain.dataaccess;

import com.fiap.cardioradar.domain.IndicadorCardiovascular;

import java.time.YearMonth;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IndicadorCardiovascularDataAccess {

    Optional<IndicadorCardiovascular> buscarPorId(
            UUID id
    );

    Optional<IndicadorCardiovascular>
    buscarPorMunicipioECompetencia(
            UUID municipioId,
            YearMonth competencia
    );

    List<IndicadorCardiovascular> listarPorMunicipio(
            UUID municipioId
    );

    boolean existePorMunicipioECompetencia(
            UUID municipioId,
            YearMonth competencia
    );

    void salvar(
            IndicadorCardiovascular indicador
    );
}