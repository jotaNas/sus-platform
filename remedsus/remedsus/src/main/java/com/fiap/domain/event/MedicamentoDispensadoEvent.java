package com.fiap.domain.event;

import java.time.LocalDateTime;
import java.util.UUID;

public record MedicamentoDispensadoEvent(
        String eventId,
        LocalDateTime occurredAt,
        String estoqueId,
        String unidadeId,
        String medicamentoId,
        int quantidade
) implements DomainEvent {

    public MedicamentoDispensadoEvent(
            String estoqueId,
            String unidadeId,
            String medicamentoId,
            int quantidade
    ) {
        this(
                UUID.randomUUID().toString(),
                LocalDateTime.now(),
                estoqueId,
                unidadeId,
                medicamentoId,
                quantidade
        );
    }
}