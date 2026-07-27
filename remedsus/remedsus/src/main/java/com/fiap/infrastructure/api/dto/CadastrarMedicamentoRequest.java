package com.fiap.infrastructure.api.dto;

import jakarta.validation.constraints.NotBlank;

public record CadastrarMedicamentoRequest(
        @NotBlank String nome,
        @NotBlank String principioAtivo,
        @NotBlank String apresentacao
) {}
