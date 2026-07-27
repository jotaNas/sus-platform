package br.com.fiap.clients;

import java.util.List;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import br.com.fiap.responses.PesquisaResponse;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;

@Path("/")
@RegisterRestClient(configKey = "dados-ibge-api")
public interface DadosIbgeClient {
    
    @GET
    @Path("/v3/agregados/{agregado}/periodos/{periodo}/variaveis/{variavel}")
    @Produces(MediaType.APPLICATION_JSON)
    List<PesquisaResponse> obterDadosPesquisa(
        @PathParam("agregado") String agregado,
        @PathParam("variavel") String variavel,
        @PathParam("periodo") String periodo,
        @QueryParam("localidades") String localidades,
        @QueryParam("classificacao") String classificacao
    );
}
