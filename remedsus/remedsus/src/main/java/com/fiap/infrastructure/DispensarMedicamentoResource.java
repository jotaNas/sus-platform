package com.fiap.infrastructure;

import com.fiap.application.handler.DispensarMedicamentoHandler;
import com.fiap.application.command.DispensarMedicamentoCommand;
import com.fiap.infrastructure.api.dto.DispensarMedicamentoRequest;
import com.fiap.infrastructure.api.dto.OperacaoEstoqueResponse;
import jakarta.validation.Valid;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/api/v1/estoques/dispensacoes")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class DispensarMedicamentoResource {

    private final DispensarMedicamentoHandler handler;

    public DispensarMedicamentoResource(DispensarMedicamentoHandler handler) {
        this.handler = handler;
    }

    @POST
    public Response dispensar(@Valid DispensarMedicamentoRequest request) {
        var command = new DispensarMedicamentoCommand(
                request.unidadeId(),
                request.medicamentoId(),
                request.quantidade()
        );

        handler.handle(command);

        return Response.ok(
                new OperacaoEstoqueResponse("Medicamento dispensado com sucesso.")
        ).build();
    }
}
