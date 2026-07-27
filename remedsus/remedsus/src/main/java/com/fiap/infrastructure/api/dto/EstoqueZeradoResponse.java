package com.fiap.infrastructure.api.dto;

public record EstoqueZeradoResponse(
        String estoqueId,
        String unidadeId,
        String nomeUnidade,
        String medicamentoId,
        String nomeMedicamento
) {}
