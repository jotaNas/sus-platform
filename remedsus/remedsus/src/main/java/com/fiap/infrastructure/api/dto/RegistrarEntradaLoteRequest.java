package com.fiap.infrastructure.api.dto;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.time.LocalDate;

public record RegistrarEntradaLoteRequest(

        @NotBlank
        String unidadeId,

        @NotBlank
        String medicamentoId,

        @NotBlank
        String numeroLote,

        @NotNull
        @Future
        LocalDate validade,

        @Positive
        int quantidade

) {}
