package com.fiap.infrastructure.api;

import com.fiap.application.command.CadastrarUnidadeSaudeCommand;
import com.fiap.application.handler.CadastrarUnidadeSaudeHandler;
import com.fiap.infrastructure.api.dto.CadastrarUnidadeSaudeRequest;
import com.fiap.infrastructure.api.dto.CriacaoResponse;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/api/v1/unidades-saude")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UnidadeSaudeResource {

    private final CadastrarUnidadeSaudeHandler handler;

    public UnidadeSaudeResource(CadastrarUnidadeSaudeHandler handler) {
        this.handler = handler;
    }

    @POST
    public Response cadastrar(CadastrarUnidadeSaudeRequest request) {
        String id = handler.handle(new CadastrarUnidadeSaudeCommand(
                request.nome(),
                request.tipo(),
                request.municipio(),
                request.bairro()
        ));

        return Response.status(Response.Status.CREATED)
                .entity(new CriacaoResponse(id, "Unidade de saúde cadastrada com sucesso."))
                .build();
    }
}