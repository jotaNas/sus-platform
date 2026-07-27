package com.fiap.infrastructure.api.dto;

import java.time.LocalDate;
import java.util.List;

public record EstoqueResponse(
        String id,
        String unidadeId,
        String medicamentoId,
        int saldoAtual,
        int estoqueMinimo,
        List<LoteResponse> lotes
) {

    public record LoteResponse(
            String id,
            String numero,
            LocalDate validade,
            int quantidadeAtual
    ) {}
}
