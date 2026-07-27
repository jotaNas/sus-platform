package com.fiap.cardioradar.infrastructure.api;



import com.fiap.cardioradar.application.command.CadastrarIndicadorCardiovascularCommand;
import com.fiap.cardioradar.application.handler.CadastrarIndicadorCardiovascularHandler;
import com.fiap.cardioradar.infrastructure.api.dto.CadastrarIndicadorCardiovascularRequest;
import com.fiap.cardioradar.infrastructure.api.dto.CriacaoResponse;
import jakarta.validation.Valid;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.time.YearMonth;
import java.util.UUID;

@Path("/api/v1/indicadores-cardiovasculares")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class IndicadorCardiovascularResource {

    private final CadastrarIndicadorCardiovascularHandler handler;

    public IndicadorCardiovascularResource(
            CadastrarIndicadorCardiovascularHandler handler
    ) {
        this.handler = handler;
    }

    @POST
    public Response cadastrar(
            @Valid CadastrarIndicadorCardiovascularRequest request
    ) {
        UUID id = handler.handle(
                new CadastrarIndicadorCardiovascularCommand(
                        request.municipioId(),
                        YearMonth.parse(request.competencia()),
                        request.populacaoEstimada(),
                        request.populacaoIdosa(),
                        request.atendimentosHipertensao(),
                        request.atendimentosDiabetes(),
                        request.internacoesCardiovasculares(),
                        request.obitosCardiovasculares(),
                        request.procedimentosCardiovasculares(),
                        request.fonte()
                )
        );

        return Response.status(Response.Status.CREATED)
                .entity(
                        new CriacaoResponse(
                                id,
                                "Indicador cardiovascular cadastrado com sucesso."
                        )
                )
                .build();
    }
}