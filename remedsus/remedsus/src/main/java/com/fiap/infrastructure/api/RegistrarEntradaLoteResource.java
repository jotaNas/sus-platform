package com.fiap.infrastructure.api;

import com.fiap.application.handler.RegistrarEntradaLoteHandler;
import com.fiap.application.command.RegistrarEntradaLoteCommand;
import com.fiap.infrastructure.api.dto.OperacaoEstoqueResponse;
import com.fiap.infrastructure.api.dto.RegistrarEntradaLoteRequest;
import jakarta.validation.Valid;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/api/v1/estoques/entradas")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class RegistrarEntradaLoteResource {

    private final RegistrarEntradaLoteHandler handler;

    public RegistrarEntradaLoteResource(RegistrarEntradaLoteHandler handler) {
        this.handler = handler;
    }

    @POST
    public Response registrar(@Valid RegistrarEntradaLoteRequest request) {
        var command = new RegistrarEntradaLoteCommand(
                request.unidadeId(),
                request.medicamentoId(),
                request.numeroLote(),
                request.validade(),
                request.quantidade()
        );

        handler.handle(command);

        return Response.status(Response.Status.CREATED)
                .entity(new OperacaoEstoqueResponse("Entrada de lote registrada com sucesso."))
                .build();
    }
}