package com.fiap.application.handler.subscriber;

import com.fiap.application.port.EventSubscriber;
import com.fiap.domain.dataaccess.AlertaDataAccess;
import com.fiap.domain.event.DomainEvent;
import com.fiap.domain.event.MedicamentoZeradoEvent;
import com.fiap.domain.model.Alerta;

import com.fiap.domain.vo.GravidadeAlerta;
import com.fiap.domain.vo.TipoAlerta;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

import java.util.UUID;

@ApplicationScoped
public class GerarAlertaMedicamentoZeradoSubscriber
        implements EventSubscriber<MedicamentoZeradoEvent> {

    private final AlertaDataAccess alertaDataAccess;

    public GerarAlertaMedicamentoZeradoSubscriber(AlertaDataAccess alertaDataAccess) {
        this.alertaDataAccess = alertaDataAccess;
    }

    @Override
    public boolean supports(DomainEvent event) {
        return event instanceof MedicamentoZeradoEvent;
    }

    @Override
    @Transactional
    public void onEvent(MedicamentoZeradoEvent event) {
        boolean jaExiste = alertaDataAccess
                .buscarAbertoPorTipoUnidadeMedicamento(
                        TipoAlerta.ESTOQUE_ZERADO,
                        event.unidadeId(),
                        event.medicamentoId()
                )
                .isPresent();

        if (jaExiste) {
            return;
        }

        Alerta alerta = new Alerta(
                UUID.randomUUID().toString(),
                TipoAlerta.ESTOQUE_ZERADO,
                GravidadeAlerta.CRITICA,
                event.unidadeId(),
                event.medicamentoId(),
                null,
                null,
                "Medicamento zerado na unidade.",
                event.occurredAt()
        );

        alertaDataAccess.salvar(alerta);
    }
}
