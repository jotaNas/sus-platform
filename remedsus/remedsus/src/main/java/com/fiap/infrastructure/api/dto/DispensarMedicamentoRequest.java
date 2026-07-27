package com.fiap.infrastructure.api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

public record DispensarMedicamentoRequest(

        @NotBlank
        String unidadeId,

        @NotBlank
        String medicamentoId,

        @Positive
        int quantidade

) {}
