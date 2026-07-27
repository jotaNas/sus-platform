package com.fiap.application.handler.subscriber;

import com.fiap.application.port.EventSubscriber;
import com.fiap.domain.event.DomainEvent;
import com.fiap.domain.event.EstoqueMinimoAtingidoEvent;
import jakarta.enterprise.context.ApplicationScoped;
import org.jboss.logging.Logger;

@ApplicationScoped
public class EnviarAlertaDeReposicaoHandler
        implements EventSubscriber<EstoqueMinimoAtingidoEvent> {

    private static final Logger LOG = Logger.getLogger(EnviarAlertaDeReposicaoHandler.class);

    @Override
    public boolean supports(DomainEvent event) {
        return event instanceof EstoqueMinimoAtingidoEvent;
    }

    @Override
    public void onEvent(EstoqueMinimoAtingidoEvent event) {
        LOG.warnf(
                "ALERTA DE REPOSIÇÃO: unidadeId=%s medicamentoId=%s saldoAtual=%d estoqueMinimo=%d",
                event.unidadeId(),
                event.medicamentoId(),
                event.saldoAtual(),
                event.estoqueMinimo()
        );
    }
}
