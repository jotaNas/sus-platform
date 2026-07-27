package com.fiap.cardioradar.infrastructure.api.dto;

import com.fiap.cardioradar.domain.vo.FonteDado;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.PositiveOrZero;

import java.util.UUID;

import jakarta.validation.constraints.Positive;


public record CadastrarIndicadorCardiovascularRequest(

        @NotNull(message = "O município é obrigatório.")
        UUID municipioId,

        @NotBlank(message = "A competência é obrigatória.")
        @Pattern(
                regexp = "^\\d{4}-(0[1-9]|1[0-2])$",
                message = "A competência deve estar no formato yyyy-MM."
        )
        String competencia,

        @Positive(
                message = "A população estimada deve ser maior que zero."
        )
        int populacaoEstimada,

        @PositiveOrZero(
                message = "A população idosa não pode ser negativa."
        )
        int populacaoIdosa,

        @PositiveOrZero(
                message = "Os atendimentos de hipertensão não podem ser negativos."
        )
        int atendimentosHipertensao,

        @PositiveOrZero(
                message = "Os atendimentos de diabetes não podem ser negativos."
        )
        int atendimentosDiabetes,

        @PositiveOrZero(
                message = "As internações cardiovasculares não podem ser negativas."
        )
        int internacoesCardiovasculares,

        @PositiveOrZero(
                message = "Os óbitos cardiovasculares não podem ser negativos."
        )
        int obitosCardiovasculares,

        @PositiveOrZero(
                message = "Os procedimentos cardiovasculares não podem ser negativos."
        )
        int procedimentosCardiovasculares,

        @NotNull(message = "A fonte do dado é obrigatória.")
        FonteDado fonte
) {
}