package com.fiap.application.handler;

import com.fiap.application.port.DomainEventPublisher;
import com.fiap.application.command.RegistrarEntradaLoteCommand;
import com.fiap.domain.model.Estoque;
import com.fiap.domain.dataaccess.EstoqueDataAccess;
import com.fiap.domain.dataaccess.MedicamentoDataAccess;
import com.fiap.domain.dataaccess.UnidadeSaudeDataAccess;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.UUID;

import jakarta.transaction.Transactional;

@ApplicationScoped
public class RegistrarEntradaLoteHandler {

    private final EstoqueDataAccess estoqueRepository;
    private final MedicamentoDataAccess medicamentoRepository;
    private final UnidadeSaudeDataAccess unidadeSaudeRepository;
    private final DomainEventPublisher eventPublisher;

    public RegistrarEntradaLoteHandler(
            EstoqueDataAccess estoqueRepository,
            MedicamentoDataAccess medicamentoRepository,
            UnidadeSaudeDataAccess unidadeSaudeRepository,
            DomainEventPublisher eventPublisher
    ) {
        this.estoqueRepository = estoqueRepository;
        this.medicamentoRepository = medicamentoRepository;
        this.unidadeSaudeRepository = unidadeSaudeRepository;
        this.eventPublisher = eventPublisher;
    }

    @Transactional
    public void handle(RegistrarEntradaLoteCommand command) {
        var unidade = unidadeSaudeRepository.buscarPorId(command.unidadeId())
                .orElseThrow(() -> new IllegalStateException("Unidade de saúde não encontrada."));

        if (!unidade.isAtiva()) {
            throw new IllegalStateException("Não é possível registrar estoque em unidade de saúde inativa.");
        }

        var medicamento = medicamentoRepository.buscarPorId(command.medicamentoId())
                .orElseThrow(() -> new IllegalStateException("Medicamento não encontrado."));

        if (!medicamento.isAtivo()) {
            throw new IllegalStateException("Não é possível registrar estoque para medicamento inativo.");
        }

        Estoque estoque = estoqueRepository
                .buscarPorUnidadeEMedicamento(
                        command.unidadeId(),
                        command.medicamentoId()
                )
                .orElseGet(() -> new Estoque(
                        UUID.randomUUID().toString(),
                        command.unidadeId(),
                        command.medicamentoId(),
                        20
                ));

        estoque.registrarEntrada(
                UUID.randomUUID().toString(),
                command.numeroLote(),
                command.validade(),
                command.quantidade()
        );

        estoqueRepository.salvar(estoque);

        eventPublisher.publish(estoque.pullEvents());
    }
}