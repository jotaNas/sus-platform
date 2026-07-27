package com.fiap.application.command;

public record CadastrarMedicamentoCommand(
        String nome,
        String principioAtivo,
        String apresentacao
) {}
