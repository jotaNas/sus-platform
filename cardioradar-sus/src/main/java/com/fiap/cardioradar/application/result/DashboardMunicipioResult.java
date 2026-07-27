package com.fiap.cardioradar.application.result;

import com.fiap.cardioradar.domain.RiscoMunicipal;

import java.util.List;
import java.util.UUID;

public record DashboardMunicipioResult(
        UUID municipioId,
        RiscoMunicipal riscoAtual,
        int quantidadeCompetencias,
        long quantidadePeriodosCriticos,
        double menorIndice,
        double maiorIndice,
        double mediaIndice,
        List<RiscoMunicipal> historico
) {
}
