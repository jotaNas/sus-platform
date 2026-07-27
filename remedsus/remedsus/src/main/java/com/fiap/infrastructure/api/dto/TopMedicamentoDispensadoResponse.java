package com.fiap.infrastructure.api.dto;

public record TopMedicamentoDispensadoResponse(
        String medicamentoId,
        String nomeMedicamento,
        Long quantidadeTotal
) {}
