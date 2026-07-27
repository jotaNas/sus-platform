package com.fiap.application.command;

import java.time.LocalDate;

public record RegistrarEntradaLoteCommand(
        String unidadeId,
        String medicamentoId,
        String numeroLote,
        LocalDate validade,
        int quantidade
) {}
