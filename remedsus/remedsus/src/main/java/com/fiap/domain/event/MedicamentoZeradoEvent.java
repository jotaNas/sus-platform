package com.fiap.domain.event;

import java.time.LocalDateTime;
import java.util.UUID;

public record MedicamentoZeradoEvent(
        String eventId,
        LocalDateTime occurredAt,
        String unidadeId,
        String medicamentoId
) implements DomainEvent {

    public MedicamentoZeradoEvent(
            String unidadeId,
            String medicamentoId
    ) {
        this(
                UUID.randomUUID().toString(),
                LocalDateTime.now(),
                unidadeId,
                medicamentoId
        );
    }
}