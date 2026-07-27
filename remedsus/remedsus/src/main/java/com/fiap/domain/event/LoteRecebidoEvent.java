package com.fiap.domain.event;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

public record LoteRecebidoEvent(
        String eventId,
        LocalDateTime occurredAt,
        String estoqueId,
        String unidadeId,
        String medicamentoId,
        String loteId,
        int quantidade,
        LocalDate validade
) implements DomainEvent {

    public LoteRecebidoEvent(
            String estoqueId,
            String unidadeId,
            String medicamentoId,
            String loteId,
            int quantidade,
            LocalDate validade
    ) {
        this(
                UUID.randomUUID().toString(),
                LocalDateTime.now(),
                estoqueId,
                unidadeId,
                medicamentoId,
                loteId,
                quantidade,
                validade
        );
    }
}