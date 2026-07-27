package com.fiap.infrastructure.api.dto;

import com.fiap.domain.vo.TipoMovimentacaoEstoque;

import java.time.LocalDateTime;

public record MovimentacaoEstoqueResponse(
        String id,
        String estoqueId,
        String unidadeId,
        String medicamentoId,
        TipoMovimentacaoEstoque tipo,
        int quantidade,
        LocalDateTime realizadaEm,
        String loteId,
        String origemEvento
) {}
