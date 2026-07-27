package com.fiap.infrastructure.messaging;

import com.fiap.application.port.DomainEventPublisher;
import com.fiap.domain.event.DomainEvent;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;

import com.fiap.application.port.EventBus;


@ApplicationScoped
public class InternalDomainEventPublisher implements DomainEventPublisher {

    private final EventBus eventBus;

    public InternalDomainEventPublisher(EventBus eventBus) {
        this.eventBus = eventBus;
    }

    @Override
    public void publish(List<DomainEvent> events) {
        eventBus.publishAll(events);
    }
}