package com.fiap.cardioradar.infrastructure.api;

import com.fiap.cardioradar.application.command.CalcularRiscoMunicipalCommand;
import com.fiap.cardioradar.application.handler.*;
import com.fiap.cardioradar.application.query.ConsultarDashboardMunicipioQuery;
import com.fiap.cardioradar.application.query.ConsultarRankingMunicipiosQuery;
import com.fiap.cardioradar.application.query.ConsultarRiscoMunicipalQuery;
import com.fiap.cardioradar.application.query.ConsultarTendenciaTemporalQuery;
import com.fiap.cardioradar.application.result.DashboardMunicipioResult;
import com.fiap.cardioradar.domain.RiscoMunicipal;

import com.fiap.cardioradar.infrastructure.api.dto.*;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.time.YearMonth;
import java.util.List;
import java.util.UUID;

import com.fiap.cardioradar.domain.PressaoMedicamento;


@Path("/api/v1/riscos")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class RiscoMunicipalResource {

    private final CalcularRiscoMunicipalHandler calcularHandler;
    private final ConsultarRiscoMunicipalHandler consultarHandler;
    private final ConsultarTendenciaTemporalHandler tendenciaHandler;
    private final ConsultarRankingMunicipiosHandler rankingHandler;
    private final ConsultarDashboardMunicipioHandler dashboardHandler;


    public RiscoMunicipalResource(
            CalcularRiscoMunicipalHandler calcularHandler,
            ConsultarRiscoMunicipalHandler consultarHandler,
            ConsultarTendenciaTemporalHandler temporalHandler,
            ConsultarRankingMunicipiosHandler rankingHandler,
            ConsultarDashboardMunicipioHandler dashboardHandler
    ) {
        this.calcularHandler = calcularHandler;
        this.consultarHandler = consultarHandler;
        this.tendenciaHandler = temporalHandler;
        this.rankingHandler = rankingHandler;
        this.dashboardHandler = dashboardHandler;
    }

    @POST
    @Path("/calcular")
    public Response calcular(
            @Valid CalcularRiscoMunicipalRequest request
    ) {
        PressaoMedicamento pressaoMedicamento =
                new PressaoMedicamento(
                        request.pressaoMedicamentoId(),
                        request.municipioId(),
                        request.medicamento(),
                        request.consumoMensalMedio(),
                        request.estoqueAtual()
                );

        CalcularRiscoMunicipalCommand command =
                new CalcularRiscoMunicipalCommand(
                        request.municipioId(),
                        YearMonth.parse(request.competencia()),
                        pressaoMedicamento
                );

        RiscoMunicipal risco =
                calcularHandler.handle(command);

        return Response.status(Response.Status.CREATED)
                .entity(toResponse(risco))
                .build();
    }

    @GET
    @Path("/municipios/{municipioId}")
    public Response consultar(
            @PathParam("municipioId") UUID municipioId,
            @QueryParam("competencia") String competencia
    ) {
        if (competencia == null || competencia.isBlank()) {
            throw new IllegalArgumentException(
                    "O parâmetro competencia é obrigatório."
            );
        }

        RiscoMunicipal risco =
                consultarHandler.handle(
                        new ConsultarRiscoMunicipalQuery(
                                municipioId,
                                YearMonth.parse(competencia)
                        )
                );

        return Response.ok(
                toResponse(risco)
        ).build();
    }

    private RiscoMunicipalResponse toResponse(
            RiscoMunicipal risco
    ) {
        return new RiscoMunicipalResponse(
                risco.getId(),
                risco.getMunicipioId(),
                risco.getIndicadorId(),
                risco.getCompetencia().toString(),
                risco.getIndicePressaoCardiovascular(),
                risco.getNivel(),
                risco.getTendencia(),
                risco.getCalculadoEm()
        );
    }

    @GET
    @Path("/municipios/{municipioId}/tendencia")
    public Response consultarTendencia(
            @PathParam("municipioId") UUID municipioId
    ) {
        List<PontoTendenciaRiscoResponse> response =
                tendenciaHandler
                        .handle(
                                new ConsultarTendenciaTemporalQuery(
                                        municipioId
                                )
                        )
                        .stream()
                        .map(risco ->
                                new PontoTendenciaRiscoResponse(
                                        risco.getCompetencia().toString(),
                                        risco.getIndicePressaoCardiovascular(),
                                        risco.getNivel(),
                                        risco.getTendencia()
                                )
                        )
                        .toList();

        return Response.ok(response).build();
    }

    @GET
    @Path("/ranking")
    public Response consultarRanking(
            @QueryParam("competencia") String competencia,
            @QueryParam("limite") @DefaultValue("10") int limite
    ) {
        if (competencia == null || competencia.isBlank()) {
            throw new IllegalArgumentException(
                    "O parâmetro competencia é obrigatório."
            );
        }

        YearMonth competenciaConvertida;

        try {
            competenciaConvertida = YearMonth.parse(competencia);
        } catch (Exception exception) {
            throw new IllegalArgumentException(
                    "A competência deve estar no formato yyyy-MM."
            );
        }

        List<RiscoMunicipal> riscos =
                rankingHandler.handle(
                        new ConsultarRankingMunicipiosQuery(
                                competenciaConvertida,
                                limite
                        )
                );

        List<RankingMunicipioResponse> response =
                java.util.stream.IntStream
                        .range(0, riscos.size())
                        .mapToObj(indice -> {
                            RiscoMunicipal risco = riscos.get(indice);

                            return new RankingMunicipioResponse(
                                    indice + 1,
                                    risco.getId(),
                                    risco.getMunicipioId(),
                                    risco.getIndicadorId(),
                                    risco.getCompetencia().toString(),
                                    risco.getIndicePressaoCardiovascular(),
                                    risco.getNivel(),
                                    risco.getTendencia()
                            );
                        })
                        .toList();

        return Response.ok(response).build();
    }

    @GET
    @Path("/municipios/{municipioId}/dashboard")
    public Response consultarDashboard(
            @PathParam("municipioId") UUID municipioId
    ) {
        DashboardMunicipioResult resultado =
                dashboardHandler.handle(
                        new ConsultarDashboardMunicipioQuery(
                                municipioId
                        )
                );

        RiscoMunicipal riscoAtual =
                resultado.riscoAtual();

        DashboardMunicipioResponse response =
                new DashboardMunicipioResponse(
                        resultado.municipioId(),
                        new RiscoAtualDashboardResponse(
                                riscoAtual.getId(),
                                riscoAtual.getIndicadorId(),
                                riscoAtual.getCompetencia().toString(),
                                riscoAtual.getIndicePressaoCardiovascular(),
                                riscoAtual.getNivel(),
                                riscoAtual.getTendencia(),
                                riscoAtual.getCalculadoEm()
                        ),
                        new ResumoDashboardMunicipioResponse(
                                resultado.quantidadeCompetencias(),
                                resultado.quantidadePeriodosCriticos(),
                                resultado.menorIndice(),
                                resultado.maiorIndice(),
                                resultado.mediaIndice()
                        ),
                        resultado.historico()
                                .stream()
                                .map(risco ->
                                        new PontoTendenciaRiscoResponse(
                                                risco.getCompetencia().toString(),
                                                risco.getIndicePressaoCardiovascular(),
                                                risco.getNivel(),
                                                risco.getTendencia()
                                        )
                                )
                                .toList()
                );

        return Response.ok(response).build();
    }
}