package com.fiap.application.handler.subscriber;

import com.fiap.application.port.EventSubscriber;
import com.fiap.domain.dataaccess.MovimentacaoEstoqueDataAccess;
import com.fiap.domain.event.DomainEvent;
import com.fiap.domain.event.MedicamentoDispensadoEvent;
import com.fiap.domain.model.MovimentacaoEstoque;
import com.fiap.domain.vo.TipoMovimentacaoEstoque;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

import java.util.UUID;

@ApplicationScoped
public class RegistrarMovimentacaoDispensacaoSubscriber
        implements EventSubscriber<MedicamentoDispensadoEvent> {

    private final MovimentacaoEstoqueDataAccess movimentacaoDataAccess;

    public RegistrarMovimentacaoDispensacaoSubscriber(MovimentacaoEstoqueDataAccess movimentacaoDataAccess) {
        this.movimentacaoDataAccess = movimentacaoDataAccess;
    }

    @Override
    public boolean supports(DomainEvent event) {
        return event instanceof MedicamentoDispensadoEvent;
    }

    @Override
    @Transactional
    public void onEvent(MedicamentoDispensadoEvent event) {
        MovimentacaoEstoque movimentacao = new MovimentacaoEstoque(
                UUID.randomUUID().toString(),
                event.estoqueId(),
                event.unidadeId(),
                event.medicamentoId(),
                TipoMovimentacaoEstoque.DISPENSACAO,
                event.quantidade(),
                event.occurredAt(),
                null,
                event.getClass().getSimpleName()
        );

        movimentacaoDataAccess.salvar(movimentacao);
    }
}