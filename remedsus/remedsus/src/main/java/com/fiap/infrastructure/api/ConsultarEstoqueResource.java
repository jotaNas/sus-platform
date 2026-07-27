package com.fiap.infrastructure.api;

import com.fiap.domain.dataaccess.EstoqueDataAccess;
import com.fiap.infrastructure.api.dto.EstoqueResponse;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/api/v1/estoques")
@Produces(MediaType.APPLICATION_JSON)
public class ConsultarEstoqueResource {

    private final EstoqueDataAccess estoqueDataAccess;

    public ConsultarEstoqueResource(EstoqueDataAccess estoqueDataAccess) {
        this.estoqueDataAccess = estoqueDataAccess;
    }

    @GET
    @Path("/{unidadeId}/{medicamentoId}")
    public EstoqueResponse consultar(
            @PathParam("unidadeId") String unidadeId,
            @PathParam("medicamentoId") String medicamentoId
    ) {
        var estoque = estoqueDataAccess
                .buscarPorUnidadeEMedicamento(unidadeId, medicamentoId)
                .orElseThrow(() -> new NotFoundException("Estoque não encontrado."));

        var lotes = estoque.getLotes()
                .stream()
                .map(lote -> new EstoqueResponse.LoteResponse(
                        lote.getId(),
                        lote.getNumero(),
                        lote.getValidade(),
                        lote.getQuantidadeAtual()
                ))
                .toList();

        return new EstoqueResponse(
                estoque.getId(),
                estoque.getUnidadeId(),
                estoque.getMedicamentoId(),
                estoque.getSaldoAtual(),
                estoque.getEstoqueMinimo(),
                lotes
        );
    }
}
