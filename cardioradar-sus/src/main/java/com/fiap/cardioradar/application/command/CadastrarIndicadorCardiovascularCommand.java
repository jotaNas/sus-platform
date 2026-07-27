package com.fiap.cardioradar.application.command;

import com.fiap.cardioradar.domain.vo.FonteDado;

import java.time.YearMonth;
import java.util.UUID;


public record CadastrarIndicadorCardiovascularCommand(
        UUID municipioId,
        YearMonth competencia,
        int populacaoEstimada,
        int populacaoIdosa,
        int atendimentosHipertensao,
        int atendimentosDiabetes,
        int internacoesCardiovasculares,
        int obitosCardiovasculares,
        int procedimentosCardiovasculares,
        FonteDado fonte
) {
}
