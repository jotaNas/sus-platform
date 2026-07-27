package com.fiap.application.handler.subscriber;

import com.fiap.application.port.EventSubscriber;
import com.fiap.domain.dataaccess.AlertaDataAccess;
import com.fiap.domain.event.DomainEvent;
import com.fiap.domain.event.MedicamentoProximoDoVencimentoEvent;
import com.fiap.domain.model.Alerta;

import com.fiap.domain.vo.GravidadeAlerta;
import com.fiap.domain.vo.TipoAlerta;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

import java.util.UUID;

@ApplicationScoped
public class GerarAlertaVencimentoProximoSubscriber
        implements EventSubscriber<MedicamentoProximoDoVencimentoEvent> {

    private final AlertaDataAccess alertaDataAccess;

    public GerarAlertaVencimentoProximoSubscriber(AlertaDataAccess alertaDataAccess) {
        this.alertaDataAccess = alertaDataAccess;
    }

    @Override
    public boolean supports(DomainEvent event) {
        return event instanceof MedicamentoProximoDoVencimentoEvent;
    }

    @Override
    @Transactional
    public void onEvent(MedicamentoProximoDoVencimentoEvent event) {
        boolean jaExiste = alertaDataAccess
                .buscarAbertoPorTipoUnidadeMedicamento(
                        TipoAlerta.VENCIMENTO_PROXIMO,
                        event.unidadeId(),
                        event.medicamentoId()
                )
                .isPresent();

        if (jaExiste) {
            return;
        }

        Alerta alerta = new Alerta(
                UUID.randomUUID().toString(),
                TipoAlerta.VENCIMENTO_PROXIMO,
                GravidadeAlerta.MEDIA,
                event.unidadeId(),
                event.medicamentoId(),
                null,
                event.loteId(),
                "Medicamento próximo do vencimento. Validade: " + event.validade(),
                event.occurredAt()
        );

        alertaDataAccess.salvar(alerta);
    }
}
