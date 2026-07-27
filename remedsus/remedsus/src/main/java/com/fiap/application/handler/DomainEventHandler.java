package com.fiap.application.handler;

import com.fiap.domain.event.DomainEvent;

public interface DomainEventHandler<T extends DomainEvent> {
    boolean supports(DomainEvent event);
    void handle(T event);
}
