package com.fiap.application.command;

public record DispensarMedicamentoCommand(
        String unidadeId,
        String medicamentoId,
        int quantidade
) {}
