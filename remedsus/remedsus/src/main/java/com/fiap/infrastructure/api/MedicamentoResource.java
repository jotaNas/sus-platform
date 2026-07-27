package com.fiap.infrastructure.api;

import com.fiap.application.handler.CadastrarMedicamentoHandler;
import com.fiap.application.command.CadastrarMedicamentoCommand;
import com.fiap.infrastructure.api.dto.CadastrarMedicamentoRequest;
import com.fiap.infrastructure.api.dto.CriacaoResponse;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/api/v1/medicamentos")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class MedicamentoResource {

    private final CadastrarMedicamentoHandler handler;

    public MedicamentoResource(CadastrarMedicamentoHandler handler) {
        this.handler = handler;
    }

    @POST
    public Response cadastrar(CadastrarMedicamentoRequest request) {
        System.out.println("REQUEST MEDICAMENTO = " + request);

        String id = handler.handle(new CadastrarMedicamentoCommand(
                request.nome(),
                request.principioAtivo(),
                request.apresentacao()
        ));

        return Response.status(Response.Status.CREATED)
                .entity(new CriacaoResponse(id, "Medicamento cadastrado com sucesso."))
                .build();
    }
}