package com.fiap.application.handler.subscriber;

import com.fiap.application.port.EventSubscriber;
import com.fiap.domain.dataaccess.AlertaDataAccess;
import com.fiap.domain.event.DomainEvent;
import com.fiap.domain.event.EstoqueMinimoAtingidoEvent;
import com.fiap.domain.model.Alerta;
import com.fiap.domain.vo.GravidadeAlerta;
import com.fiap.domain.vo.TipoAlerta;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

import java.util.UUID;

@ApplicationScoped
public class GerarAlertaEstoqueMinimoSubscriber
        implements EventSubscriber<EstoqueMinimoAtingidoEvent> {

    private final AlertaDataAccess alertaDataAccess;

    public GerarAlertaEstoqueMinimoSubscriber(AlertaDataAccess alertaDataAccess) {
        this.alertaDataAccess = alertaDataAccess;
    }

    @Override
    public boolean supports(DomainEvent event) {
        return event instanceof EstoqueMinimoAtingidoEvent;
    }

    @Override
    @Transactional
    public void onEvent(EstoqueMinimoAtingidoEvent event) {
        boolean jaExiste = alertaDataAccess
                .buscarAbertoPorTipoUnidadeMedicamento(
                        TipoAlerta.ESTOQUE_MINIMO,
                        event.unidadeId(),
                        event.medicamentoId()
                )
                .isPresent();

        if (jaExiste) {
            return;
        }

        Alerta alerta = new Alerta(
                UUID.randomUUID().toString(),
                TipoAlerta.ESTOQUE_MINIMO,
                GravidadeAlerta.ALTA,
                event.unidadeId(),
                event.medicamentoId(),
                null,
                null,
                "Estoque mínimo atingido. Saldo atual: " + event.saldoAtual(),
                event.occurredAt()
        );

        alertaDataAccess.salvar(alerta);
    }
}
