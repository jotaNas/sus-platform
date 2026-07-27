package com.fiap.cardioradar.domain.event;

import com.fiap.cardioradar.domain.vo.FonteDado;

import java.time.LocalDateTime;
import java.time.YearMonth;
import java.util.UUID;

public record IndicadorCardiovascularRegistradoEvent(
        String eventId,
        LocalDateTime occurredAt,
        String indicadorId,
        String municipioId,
        YearMonth competencia,
        FonteDado fonte
) implements DomainEvent {

    public IndicadorCardiovascularRegistradoEvent(
            String indicadorId,
            String municipioId,
            YearMonth competencia,
            FonteDado fonte
    ) {
        this(
                UUID.randomUUID().toString(),
                LocalDateTime.now(),
                indicadorId,
                municipioId,
                competencia,
                fonte
        );
    }
}
