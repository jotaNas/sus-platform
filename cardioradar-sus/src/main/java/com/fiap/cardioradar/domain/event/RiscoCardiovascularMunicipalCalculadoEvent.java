package com.fiap.cardioradar.domain.event;

import com.fiap.cardioradar.domain.vo.NivelRisco;
import com.fiap.cardioradar.domain.vo.TendenciaRisco;

import java.time.LocalDateTime;
import java.time.YearMonth;
import java.util.UUID;

public record RiscoCardiovascularMunicipalCalculadoEvent(
        String eventId,
        LocalDateTime occurredAt,
        String riscoId,
        String municipioId,
        YearMonth competencia,
        double indice,
        NivelRisco nivel,
        TendenciaRisco tendencia
) implements DomainEvent {

    public RiscoCardiovascularMunicipalCalculadoEvent(
            String riscoId,
            String municipioId,
            YearMonth competencia,
            double indice,
            NivelRisco nivel,
            TendenciaRisco tendencia
    ) {
        this(
                UUID.randomUUID().toString(),
                LocalDateTime.now(),
                riscoId,
                municipioId,
                competencia,
                indice,
                nivel,
                tendencia
        );
    }
}
