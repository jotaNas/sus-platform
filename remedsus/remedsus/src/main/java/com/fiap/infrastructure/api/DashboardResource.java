package com.fiap.infrastructure.api;

import com.fiap.infrastructure.api.dto.DashboardResponse;
import com.fiap.infrastructure.persistence.jpa.repository.DashboardQueryRepository;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/api/v1/dashboard")
@Produces(MediaType.APPLICATION_JSON)
public class DashboardResource {

    private final DashboardQueryRepository dashboardQueryRepository;

    public DashboardResource(DashboardQueryRepository dashboardQueryRepository) {
        this.dashboardQueryRepository = dashboardQueryRepository;
    }

    @GET
    public DashboardResponse consultar() {
        return new DashboardResponse(
                dashboardQueryRepository.totalAlertasAbertos(),
                dashboardQueryRepository.totalEstoquesZerados(),
                dashboardQueryRepository.medicamentosCriticos(),
                dashboardQueryRepository.estoquesZerados(),
                dashboardQueryRepository.lotesVencendo(),
                dashboardQueryRepository.topMedicamentosDispensados(),
                dashboardQueryRepository.consumoMensal()
        );
    }
}
