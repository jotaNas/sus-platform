package com.fiap.cardioradar.infrastructure.api.dto;



import java.util.List;
import java.util.UUID;


public record DashboardMunicipioResponse(
        UUID municipioId,
        RiscoAtualDashboardResponse riscoAtual,
        ResumoDashboardMunicipioResponse resumo,
        List<PontoTendenciaRiscoResponse> historico
) {
}
