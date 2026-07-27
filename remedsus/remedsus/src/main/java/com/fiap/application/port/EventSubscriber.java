package com.fiap.application.port;

import com.fiap.domain.event.DomainEvent;

public interface EventSubscriber<T extends DomainEvent> {

    boolean supports(DomainEvent event);

    void onEvent(T event);
}
