package com.fiap.infrastructure.api.dto;

import java.util.List;

public record DashboardResponse(
        long totalAlertasAbertos,
        long totalEstoquesZerados,
        List<MedicamentoCriticoResponse> medicamentosCriticos,
        List<EstoqueZeradoResponse> estoquesZerados,
        List<LoteVencendoResponse> lotesVencendo,
        List<TopMedicamentoDispensadoResponse> topMedicamentosDispensados,
        List<ConsumoMensalResponse> consumoMensal
) {}
