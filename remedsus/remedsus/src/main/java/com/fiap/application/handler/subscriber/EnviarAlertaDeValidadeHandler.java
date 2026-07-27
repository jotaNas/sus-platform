package com.fiap.application.handler.subscriber;

import com.fiap.application.port.EventSubscriber;
import com.fiap.domain.event.DomainEvent;
import com.fiap.domain.event.MedicamentoProximoDoVencimentoEvent;
import jakarta.enterprise.context.ApplicationScoped;
import org.jboss.logging.Logger;

@ApplicationScoped
public class EnviarAlertaDeValidadeHandler
        implements EventSubscriber<MedicamentoProximoDoVencimentoEvent> {

    private static final Logger LOG = Logger.getLogger(EnviarAlertaDeValidadeHandler.class);

    @Override
    public boolean supports(DomainEvent event) {
        return event instanceof MedicamentoProximoDoVencimentoEvent;
    }

    @Override
    public void onEvent(MedicamentoProximoDoVencimentoEvent event) {
        LOG.warnf(
                "ALERTA DE VALIDADE: unidadeId=%s medicamentoId=%s loteId=%s validade=%s",
                event.unidadeId(),
                event.medicamentoId(),
                event.loteId(),
                event.validade()
        );
    }
}
