package com.fiap.cardioradar.infrastructure.api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.util.UUID;

import jakarta.validation.constraints.Min;


public record CalcularRiscoMunicipalRequest(

        @NotNull(message = "Município é obrigatório.")
        UUID municipioId,

        @NotBlank(message = "Competência é obrigatória.")
        @Pattern(
                regexp = "\\d{4}-(0[1-9]|1[0-2])",
                message = "Competência deve possuir o formato yyyy-MM."
        )
        String competencia,

        @NotNull(message = "Id da pressão do medicamento é obrigatório.")
        UUID pressaoMedicamentoId,

        @NotBlank(message = "Medicamento é obrigatório.")
        String medicamento,

        @NotNull(message = "Consumo mensal médio é obrigatório.")
        @Min(
                value = 0,
                message = "Consumo mensal médio não pode ser negativo."
        )
        Integer consumoMensalMedio,

        @NotNull(message = "Estoque atual é obrigatório.")
        @Min(
                value = 0,
                message = "Estoque atual não pode ser negativo."
        )
        Integer estoqueAtual
) {
}