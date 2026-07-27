package com.fiap.application.port;

import com.fiap.domain.event.DomainEvent;

import java.util.List;

public interface DomainEventPublisher {

    void publish(List<DomainEvent> events);
}
