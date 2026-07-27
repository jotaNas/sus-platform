package br.com.fiap.clients;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import br.com.fiap.dtos.estabelecimento.EstabelecimentoDTO;
import br.com.fiap.responses.EstabelecimentoResponse;
import br.com.fiap.responses.EstoqueMedicamentosResponse;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;

@Path("/")
@RegisterRestClient(configKey = "dados-abertos-api")
public interface DadosAbertosClient {
    
    @GET
    @Path("/daf/estoque-medicamentos-bnafar-horus")
    @Produces(MediaType.APPLICATION_JSON)
    EstoqueMedicamentosResponse obterEstoqueMedicamentos(
        @QueryParam("limit") Integer limit,
        @QueryParam("offset") Integer offset,
        @QueryParam("codigo_uf") String codigoUf,
        @QueryParam("codigo_municipio") String codigoMunicipio,
        @QueryParam("codigo_cnes") String codigoCnes,
        @QueryParam("anomes_posicao_estoque") Integer anomesPosicaoEstoque,
        @QueryParam("data_posicao_estoque") String dataPosicaoEstoque,
        @QueryParam("codigo_catmat") String codigoCatmat,
        @QueryParam("sigla_programa_saude") String siglaProgramaSaude,
        @QueryParam("tipo_produto") String tipoProduto,
        @QueryParam("sigla_sistema_origem") String siglaSistemaOrigem
    );

    @GET
    @Path("/cnes/estabelecimentos")
    @Produces(MediaType.APPLICATION_JSON)
    EstabelecimentoResponse obterEstabelecimentos(
        @QueryParam("codigo_tipo_unidade") Integer codigoTipoUnidade,
        @QueryParam("codigo_uf") String codigoUf,
        @QueryParam("codigo_municipio") Integer codigoMunicipio,
        @QueryParam("status") Integer status,
        @QueryParam("estabelecimento_possui_centro_cirurgico") Integer centroCirurgico,
        @QueryParam("estabelecimento_possui_centro_obstetrico") Integer centroObstetrico,
        @QueryParam("data_atualizacao") String dataAtualizacao,
        @QueryParam("limit") Integer limit,
        @QueryParam("offset") Integer offset
    );

    @GET
    @Path("cnes/estabelecimentos/{codigoCnes}")
    @Produces(MediaType.APPLICATION_JSON)
    EstabelecimentoDTO obterEstabelecimentoPorCnes(@PathParam("codigoCnes") Integer codigoCnes);
}
