package com.fiap.cardioradar.infrastructure.api.dto;

import com.fiap.cardioradar.domain.vo.NivelRisco;
import com.fiap.cardioradar.domain.vo.TendenciaRisco;

import java.time.LocalDateTime;
import java.util.UUID;


public record RiscoMunicipalResponse(
        UUID id,
        UUID municipioId,
        UUID indicadorId,
        String competencia,
        double indicePressaoCardiovascular,
        NivelRisco nivel,
        TendenciaRisco tendencia,
        LocalDateTime calculadoEm
) {
}