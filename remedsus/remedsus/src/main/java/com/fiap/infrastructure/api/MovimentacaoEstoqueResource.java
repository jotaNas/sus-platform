package com.fiap.infrastructure.api;

import com.fiap.domain.dataaccess.MovimentacaoEstoqueDataAccess;
import com.fiap.infrastructure.api.dto.MovimentacaoEstoqueResponse;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import java.util.List;

@Path("/api/v1/estoques")
@Produces(MediaType.APPLICATION_JSON)
public class MovimentacaoEstoqueResource {

    private final MovimentacaoEstoqueDataAccess movimentacaoDataAccess;

    public MovimentacaoEstoqueResource(MovimentacaoEstoqueDataAccess movimentacaoDataAccess) {
        this.movimentacaoDataAccess = movimentacaoDataAccess;
    }

    @GET
    @Path("/{estoqueId}/movimentacoes")
    public List<MovimentacaoEstoqueResponse> listar(@PathParam("estoqueId") String estoqueId) {
        return movimentacaoDataAccess.listarPorEstoqueId(estoqueId)
                .stream()
                .map(m -> new MovimentacaoEstoqueResponse(
                        m.getId(),
                        m.getEstoqueId(),
                        m.getUnidadeId(),
                        m.getMedicamentoId(),
                        m.getTipo(),
                        m.getQuantidade(),
                        m.getRealizadaEm(),
                        m.getLoteId(),
                        m.getOrigemEvento()
                ))
                .toList();
    }
}
