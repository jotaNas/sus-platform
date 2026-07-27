package com.fiap.application.handler;

import com.fiap.domain.event.DomainEvent;
import com.fiap.domain.event.EstoqueMinimoAtingidoEvent;
import org.jboss.logging.Logger;

public class EstoqueMinimoAtingidoEventHandler
        implements DomainEventHandler<EstoqueMinimoAtingidoEvent> {

    private static final Logger LOG = Logger.getLogger(EstoqueMinimoAtingidoEventHandler.class);

    @Override
    public boolean supports(DomainEvent event) {
        return event instanceof EstoqueMinimoAtingidoEvent;
    }

    @Override
    public void handle(EstoqueMinimoAtingidoEvent event) {
        LOG.warnf(
                "ALERTA: Estoque mínimo atingido. unidadeId=%s medicamentoId=%s saldoAtual=%d estoqueMinimo=%d",
                event.unidadeId(),
                event.medicamentoId(),
                event.saldoAtual(),
                event.estoqueMinimo()
        );
    }
}
