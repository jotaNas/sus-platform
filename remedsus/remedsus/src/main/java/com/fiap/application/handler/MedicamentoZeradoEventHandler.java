package com.fiap.application.handler;

import com.fiap.domain.event.DomainEvent;
import com.fiap.domain.event.MedicamentoZeradoEvent;
import org.jboss.logging.Logger;

public class MedicamentoZeradoEventHandler
        implements DomainEventHandler<MedicamentoZeradoEvent> {

    private static final Logger LOG = Logger.getLogger(MedicamentoZeradoEventHandler.class);

    @Override
    public boolean supports(DomainEvent event) {
        return event instanceof MedicamentoZeradoEvent;
    }

    @Override
    public void handle(MedicamentoZeradoEvent event) {
        LOG.errorf(
                "ALERTA CRÍTICO: Medicamento zerado. unidadeId=%s medicamentoId=%s",
                event.unidadeId(),
                event.medicamentoId()
        );
    }
}
