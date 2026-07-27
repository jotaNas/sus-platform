package br.com.fiap.controllers;

import java.util.List;

import br.com.fiap.dtos.pesquisaibge.PesquisaFiltroDTO;
import br.com.fiap.responses.PesquisaResponse;
import br.com.fiap.services.DadosIbgeService;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/ibge")
public class PesquisaController {
    
    @Inject
    DadosIbgeService service;

    @POST
    @Path("/pesquisa")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public List<PesquisaResponse> obterDadosPesquisa(PesquisaFiltroDTO filtro) {

        return service.buscarDadosPesquisa(filtro);
    }
}
