package com.fiap.domain.event;

import java.time.LocalDateTime;
import java.util.UUID;

public record EstoqueMinimoAtingidoEvent(
        String eventId,
        LocalDateTime occurredAt,
        String unidadeId,
        String medicamentoId,
        int saldoAtual,
        int estoqueMinimo
) implements DomainEvent {

    public EstoqueMinimoAtingidoEvent(
            String unidadeId,
            String medicamentoId,
            int saldoAtual,
            int estoqueMinimo
    ) {
        this(
                UUID.randomUUID().toString(),
                LocalDateTime.now(),
                unidadeId,
                medicamentoId,
                saldoAtual,
                estoqueMinimo
        );
    }
}
