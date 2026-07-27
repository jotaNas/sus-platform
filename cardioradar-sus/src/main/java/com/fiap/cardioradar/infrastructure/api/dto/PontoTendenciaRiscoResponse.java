package com.fiap.cardioradar.infrastructure.api.dto;

import com.fiap.cardioradar.domain.vo.NivelRisco;
import com.fiap.cardioradar.domain.vo.TendenciaRisco;

public record PontoTendenciaRiscoResponse(
        String competencia,
        double indice,
        NivelRisco nivel,
        TendenciaRisco tendencia
) {
}