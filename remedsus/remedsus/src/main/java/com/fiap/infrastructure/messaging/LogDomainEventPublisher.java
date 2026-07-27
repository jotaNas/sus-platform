package com.fiap.infrastructure.messaging;

import com.fiap.application.port.DomainEventPublisher;
import com.fiap.domain.event.DomainEvent;
import jakarta.enterprise.context.ApplicationScoped;
import org.jboss.logging.Logger;

import java.util.List;

//@ApplicationScoped
public class LogDomainEventPublisher implements DomainEventPublisher {

    private static final Logger LOG = Logger.getLogger(LogDomainEventPublisher.class);

    @Override
    public void publish(List<DomainEvent> events) {
        events.forEach(event ->
                LOG.infof("Evento publicado: %s", event)
        );
    }
}
