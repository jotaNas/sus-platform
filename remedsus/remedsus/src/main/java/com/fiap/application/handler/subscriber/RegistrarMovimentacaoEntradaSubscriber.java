package com.fiap.application.handler.subscriber;

import com.fiap.application.port.EventSubscriber;
import com.fiap.domain.dataaccess.MovimentacaoEstoqueDataAccess;
import com.fiap.domain.event.DomainEvent;
import com.fiap.domain.event.LoteRecebidoEvent;
import com.fiap.domain.model.MovimentacaoEstoque;
import com.fiap.domain.vo.TipoMovimentacaoEstoque;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

import java.util.UUID;

@ApplicationScoped
public class RegistrarMovimentacaoEntradaSubscriber
        implements EventSubscriber<LoteRecebidoEvent> {

    private final MovimentacaoEstoqueDataAccess movimentacaoDataAccess;

    public RegistrarMovimentacaoEntradaSubscriber(MovimentacaoEstoqueDataAccess movimentacaoDataAccess) {
        this.movimentacaoDataAccess = movimentacaoDataAccess;
    }

    @Override
    public boolean supports(DomainEvent event) {
        return event instanceof LoteRecebidoEvent;
    }

    @Override
    @Transactional
    public void onEvent(LoteRecebidoEvent event) {
        MovimentacaoEstoque movimentacao = new MovimentacaoEstoque(
                UUID.randomUUID().toString(),
                event.estoqueId(),
                event.unidadeId(),
                event.medicamentoId(),
                TipoMovimentacaoEstoque.ENTRADA,
                event.quantidade(),
                event.occurredAt(),
                event.loteId(),
                event.getClass().getSimpleName()
        );

        movimentacaoDataAccess.salvar(movimentacao);
    }
}
