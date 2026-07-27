package com.fiap.cardioradar.infrastructure.api.dto;

public record ResumoDashboardMunicipioResponse(
        int quantidadeCompetencias,
        long quantidadePeriodosCriticos,
        double menorIndice,
        double maiorIndice,
        double mediaIndice
) {
}
