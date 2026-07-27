package com.fiap.infrastructure.messaging;

import com.fiap.application.port.EventBus;
import com.fiap.application.port.EventSubscriber;
import com.fiap.domain.event.DomainEvent;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Instance;
import org.jboss.logging.Logger;

import java.util.List;

@ApplicationScoped
public class InternalEventBus implements EventBus {

    private static final Logger LOG = Logger.getLogger(InternalEventBus.class);

    private final Instance<EventSubscriber<? extends DomainEvent>> subscribers;

    public InternalEventBus(Instance<EventSubscriber<? extends DomainEvent>> subscribers) {
        this.subscribers = subscribers;
    }

    @Override
    public void publish(DomainEvent event) {
        LOG.infof("EventBus recebeu evento: %s", event.getClass().getSimpleName());

        subscribers.stream()
                .filter(subscriber -> subscriber.supports(event))
                .forEach(subscriber -> dispatch(subscriber, event));
    }

    @Override
    public void publishAll(List<DomainEvent> events) {
        if (events == null || events.isEmpty()) {
            return;
        }

        events.forEach(this::publish);
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    private void dispatch(EventSubscriber subscriber, DomainEvent event) {
        subscriber.onEvent(event);
    }
}
