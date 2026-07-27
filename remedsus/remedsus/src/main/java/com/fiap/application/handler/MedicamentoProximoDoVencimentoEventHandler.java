package com.fiap.application.handler;

import com.fiap.domain.event.DomainEvent;
import com.fiap.domain.event.MedicamentoProximoDoVencimentoEvent;
import org.jboss.logging.Logger;

public class MedicamentoProximoDoVencimentoEventHandler
        implements DomainEventHandler<MedicamentoProximoDoVencimentoEvent> {

    private static final Logger LOG = Logger.getLogger(MedicamentoProximoDoVencimentoEventHandler.class);

    @Override
    public boolean supports(DomainEvent event) {
        return event instanceof MedicamentoProximoDoVencimentoEvent;
    }

    @Override
    public void handle(MedicamentoProximoDoVencimentoEvent event) {
        LOG.warnf(
                "ALERTA: Medicamento próximo do vencimento. unidadeId=%s medicamentoId=%s loteId=%s validade=%s",
                event.unidadeId(),
                event.medicamentoId(),
                event.loteId(),
                event.validade()
        );
    }
}
