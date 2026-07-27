package com.fiap.infrastructure.api.dto;

import java.time.LocalDate;

public record LoteVencendoResponse(
        String loteId,
        String estoqueId,
        String medicamentoId,
        LocalDate validade,
        int quantidadeAtual
) {}
