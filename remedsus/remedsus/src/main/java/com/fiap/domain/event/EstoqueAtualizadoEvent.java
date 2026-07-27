package com.fiap.domain.event;

import java.time.LocalDateTime;
import java.util.UUID;

public record EstoqueAtualizadoEvent(
        String eventId,
        LocalDateTime occurredAt,
        String unidadeId,
        String medicamentoId,
        int saldoAtual
) implements DomainEvent {

    public EstoqueAtualizadoEvent(
            String unidadeId,
            String medicamentoId,
            int saldoAtual
    ) {
        this(
                UUID.randomUUID().toString(),
                LocalDateTime.now(),
                unidadeId,
                medicamentoId,
                saldoAtual
        );
    }
}
