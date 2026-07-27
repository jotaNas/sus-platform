package br.com.fiap.controllers;

import java.util.List;

import br.com.fiap.dtos.estabelecimento.EstabelecimentoDTO;
import br.com.fiap.dtos.estabelecimento.EstabelecimentoFiltroDTO;
import br.com.fiap.services.DadosAbertosService;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/estabelecimento")
public class EstabelecimentoController {

    @Inject
    DadosAbertosService service;

    @POST
    @Path("/consultar")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public List<EstabelecimentoDTO> consultarEstabelecimentosLocal(EstabelecimentoFiltroDTO filtro) {

        return service.buscarEstabelecimentosComFiltro(filtro);
    }

    @GET
    @Path("consultar/{codigo_cnes}")
    @Produces(MediaType.APPLICATION_JSON)
    public EstabelecimentoDTO buscarPorCnesLocal(@PathParam("codigo_cnes") Integer codigoCnes) {
        return service.buscarEstabelecimentoPorCnes(codigoCnes);
    }
}
