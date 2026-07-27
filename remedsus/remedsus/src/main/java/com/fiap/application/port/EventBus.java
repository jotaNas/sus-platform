package com.fiap.application.port;

import com.fiap.domain.event.DomainEvent;

import java.util.List;

public interface EventBus {
    void publish(DomainEvent event);
    void publishAll(List<DomainEvent> events);
}
