package com.fiap.cardioradar.infrastructure.api;

import com.fiap.cardioradar.application.command.CadastrarMunicipioCommand;
import com.fiap.cardioradar.application.handler.CadastrarMunicipioHandler;
import com.fiap.cardioradar.infrastructure.api.dto.CadastrarMunicipioRequest;
import com.fiap.cardioradar.infrastructure.api.dto.CriacaoResponse;
import jakarta.validation.Valid;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.UUID;

@Path("/api/v1/municipios")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class MunicipioResource {

    private final CadastrarMunicipioHandler handler;

    public MunicipioResource(
            CadastrarMunicipioHandler handler
    ) {
        this.handler = handler;
    }

    @POST
    public Response cadastrar(
            @Valid CadastrarMunicipioRequest request
    ) {
        UUID id = handler.handle(
                new CadastrarMunicipioCommand(
                        request.codigoIbge(),
                        request.nome(),
                        request.uf()
                )
        );

        CriacaoResponse response =
                new CriacaoResponse(
                        id,
                        "Município cadastrado com sucesso."
                );

        return Response.status(
                        Response.Status.CREATED
                )
                .entity(response)
                .build();
    }
}
