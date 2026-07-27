package br.com.fiap.controllers;

import java.util.List;

import br.com.fiap.dtos.estoquemedicamentos.EstoqueMedicamentosDTO;
import br.com.fiap.dtos.estoquemedicamentos.EstoqueMedicamentosFiltroDTO;
import br.com.fiap.services.DadosAbertosService;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/estoque-medicamentos")
public class EstoqueMedicamentosController {

    @Inject
    DadosAbertosService service;

    @POST
    @Path("/consultar")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public List<EstoqueMedicamentosDTO> consultarEstoqueLocal(
        EstoqueMedicamentosFiltroDTO filtro
    ) {
        return service.buscarEstoqueComFiltro(filtro);
    }
}
