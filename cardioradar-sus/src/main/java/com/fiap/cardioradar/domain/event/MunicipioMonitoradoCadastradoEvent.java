package com.fiap.cardioradar.domain.event;

import java.time.LocalDateTime;
import java.util.UUID;

public record MunicipioMonitoradoCadastradoEvent(
        String eventId,
        LocalDateTime occurredAt,
        String municipioId,
        String codigoIbge,
        String nome,
        String uf
) implements DomainEvent {

    public MunicipioMonitoradoCadastradoEvent(
            String municipioId,
            String codigoIbge,
            String nome,
            String uf
    ) {
        this(
                UUID.randomUUID().toString(),
                LocalDateTime.now(),
                municipioId,
                codigoIbge,
                nome,
                uf
        );
    }
}
