package com.fiap.cardioradar.domain.event;

import java.time.LocalDateTime;

public interface DomainEvent {
    String eventId();
    LocalDateTime occurredAt();
}
