package com.fiap.infrastructure.api.dto;

public record ConsumoMensalResponse(
        String mes,
        Long quantidadeTotal
) {}
