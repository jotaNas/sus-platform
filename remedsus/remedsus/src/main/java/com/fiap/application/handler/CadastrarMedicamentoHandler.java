package com.fiap.application.handler;

import com.fiap.application.command.CadastrarMedicamentoCommand;
import com.fiap.domain.dataaccess.MedicamentoDataAccess;
import com.fiap.domain.model.Medicamento;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

import java.util.UUID;

@ApplicationScoped
public class CadastrarMedicamentoHandler {

    private final MedicamentoDataAccess repository;

    public CadastrarMedicamentoHandler(MedicamentoDataAccess repository) {
        this.repository = repository;
    }

    @Transactional
    public String handle(CadastrarMedicamentoCommand command) {
        if (repository.existePorNomeEApresentacao(command.nome(), command.apresentacao())) {
            throw new IllegalStateException("Medicamento já cadastrado com essa apresentação.");
        }

        Medicamento medicamento = new Medicamento(
                UUID.randomUUID().toString(),
                command.nome(),
                command.principioAtivo(),
                command.apresentacao()
        );

        repository.salvar(medicamento);

        return medicamento.getId();
    }
}
