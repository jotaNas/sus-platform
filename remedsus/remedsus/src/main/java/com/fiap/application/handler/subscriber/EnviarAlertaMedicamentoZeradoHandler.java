package com.fiap.application.handler.subscriber;

import com.fiap.application.port.EventSubscriber;
import com.fiap.domain.event.DomainEvent;
import com.fiap.domain.event.MedicamentoZeradoEvent;
import jakarta.enterprise.context.ApplicationScoped;
import org.jboss.logging.Logger;

@ApplicationScoped
public class EnviarAlertaMedicamentoZeradoHandler
        implements EventSubscriber<MedicamentoZeradoEvent> {

    private static final Logger LOG = Logger.getLogger(EnviarAlertaMedicamentoZeradoHandler.class);

    @Override
    public boolean supports(DomainEvent event) {
        return event instanceof MedicamentoZeradoEvent;
    }

    @Override
    public void onEvent(MedicamentoZeradoEvent event) {
        LOG.warnf (
                "ALERTA CRÍTICO: medicamento zerado. unidadeId=%s medicamentoId=%s",
                event.unidadeId(),
                event.medicamentoId()
        );
    }
}
