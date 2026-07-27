package com.fiap.domain.event;

import java.time.LocalDate;
import java.time.LocalDateTime;

import java.util.UUID;

public record MedicamentoProximoDoVencimentoEvent(
        String eventId,
        LocalDateTime occurredAt,
        String unidadeId,
        String medicamentoId,
        String loteId,
        LocalDate validade
) implements DomainEvent {

    public MedicamentoProximoDoVencimentoEvent(
            String unidadeId,
            String medicamentoId,
            String loteId,
            LocalDate validade
    ) {
        this(
                UUID.randomUUID().toString(),
                LocalDateTime.now(),
                unidadeId,
                medicamentoId,
                loteId,
                validade
        );
    }
}
