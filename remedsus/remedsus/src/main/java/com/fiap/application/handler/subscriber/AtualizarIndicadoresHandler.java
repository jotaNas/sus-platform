package com.fiap.application.handler.subscriber;

import com.fiap.application.port.EventSubscriber;
import com.fiap.domain.event.DomainEvent;
import com.fiap.domain.event.MedicamentoDispensadoEvent;
import jakarta.enterprise.context.ApplicationScoped;
import org.jboss.logging.Logger;

@ApplicationScoped
public class AtualizarIndicadoresHandler
        implements EventSubscriber<MedicamentoDispensadoEvent> {

    private static final Logger LOG = Logger.getLogger(AtualizarIndicadoresHandler.class);

    @Override
    public boolean supports(DomainEvent event) {
        return event instanceof MedicamentoDispensadoEvent;
    }

    @Override
    public void onEvent(MedicamentoDispensadoEvent event) {
        LOG.infof(
                "INDICADORES ATUALIZADOS: estoqueId=%s unidadeId=%s medicamentoId=%s quantidadeDispensada=%d",
                event.estoqueId(),
                event.unidadeId(),
                event.medicamentoId(),
                event.quantidade()
        );
    }
}
