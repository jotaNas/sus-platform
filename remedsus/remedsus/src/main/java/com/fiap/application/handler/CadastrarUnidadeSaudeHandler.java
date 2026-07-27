package com.fiap.application.handler;


import com.fiap.application.command.CadastrarUnidadeSaudeCommand;
import com.fiap.domain.dataaccess.UnidadeSaudeDataAccess;
import com.fiap.domain.model.UnidadeSaude;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

import java.util.UUID;

@ApplicationScoped
public class CadastrarUnidadeSaudeHandler {

    private final UnidadeSaudeDataAccess repository;

    public CadastrarUnidadeSaudeHandler(UnidadeSaudeDataAccess repository) {
        this.repository = repository;
    }

    @Transactional
    public String handle(CadastrarUnidadeSaudeCommand command) {
        UnidadeSaude unidade = new UnidadeSaude(
                UUID.randomUUID().toString(),
                command.nome(),
                command.tipo(),
                command.municipio(),
                command.bairro()
        );

        repository.salvar(unidade);

        return unidade.getId();
    }
}
