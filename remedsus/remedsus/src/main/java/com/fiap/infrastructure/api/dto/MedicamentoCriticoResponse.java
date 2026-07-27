package com.fiap.infrastructure.api.dto;

public record MedicamentoCriticoResponse(
        String medicamentoId,
        String nomeMedicamento,
        String unidadeId,
        String nomeUnidade,
        String tipoAlerta,
        String gravidade,
        String mensagem
) {}
