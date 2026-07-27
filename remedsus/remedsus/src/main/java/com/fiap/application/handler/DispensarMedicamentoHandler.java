package com.fiap.application.handler;

import com.fiap.application.port.DomainEventPublisher;
import com.fiap.application.command.DispensarMedicamentoCommand;
import com.fiap.domain.model.Estoque;
import com.fiap.domain.dataaccess.EstoqueDataAccess;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class DispensarMedicamentoHandler {

    private final EstoqueDataAccess estoqueDataAccess;
    private final DomainEventPublisher eventPublisher;

    public DispensarMedicamentoHandler(
            EstoqueDataAccess estoqueDataAccess,
            DomainEventPublisher eventPublisher
    ) {
        this.estoqueDataAccess = estoqueDataAccess;
        this.eventPublisher = eventPublisher;
    }

    public void handle(DispensarMedicamentoCommand command) {
        Estoque estoque = estoqueDataAccess
                .buscarPorUnidadeEMedicamento(
                        command.unidadeId(),
                        command.medicamentoId()
                )
                .orElseThrow(() -> new IllegalStateException(
                        "Estoque não encontrado para unidade e medicamento."
                ));

        estoque.dispensar(command.quantidade());

        estoqueDataAccess.salvar(estoque);
        eventPublisher.publish(estoque.pullEvents());
    }
}
