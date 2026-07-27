package com.fiap.cardioradar.infrastructure.api.dto;

import com.fiap.cardioradar.domain.vo.NivelRisco;
import com.fiap.cardioradar.domain.vo.TendenciaRisco;

import java.util.UUID;

public record RankingMunicipioResponse(
        int posicao,
        UUID riscoId,
        UUID municipioId,
        UUID indicadorId,
        String competencia,
        double indicePressaoCardiovascular,
        NivelRisco nivel,
        TendenciaRisco tendencia
) {
}
