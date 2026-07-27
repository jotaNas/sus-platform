package br.com.fiap.services;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Response;

import java.util.List;

import org.eclipse.microprofile.rest.client.inject.RestClient;

import br.com.fiap.clients.DadosAbertosClient;
import br.com.fiap.dtos.estabelecimento.EstabelecimentoDTO;
import br.com.fiap.dtos.estabelecimento.EstabelecimentoFiltroDTO;
import br.com.fiap.dtos.estoquemedicamentos.EstoqueMedicamentosDTO;
import br.com.fiap.dtos.estoquemedicamentos.EstoqueMedicamentosFiltroDTO;
import br.com.fiap.enums.EstadoUF;
import br.com.fiap.responses.EstabelecimentoResponse;
import br.com.fiap.responses.EstoqueMedicamentosResponse;


@ApplicationScoped
public class DadosAbertosService {

    @Inject
    @RestClient
    DadosAbertosClient client;

    public List<EstoqueMedicamentosDTO> buscarEstoqueComFiltro(EstoqueMedicamentosFiltroDTO filtro) {

        String codigoIbgeUf = null;

        codigoIbgeUf = verificarUf(filtro.getUf());

        EstoqueMedicamentosResponse response = client.obterEstoqueMedicamentos(
            filtro.getLimit(),
            filtro.getOffset(),
            codigoIbgeUf,
            filtro.getCodigoMunicipio(),
            filtro.getCodigoCnes(),
            filtro.getAnomesPosicaoEstoque(),
            filtro.getDataPosicaoEstoque(),
            filtro.getCodigoCatmat(),
            filtro.getSiglaProgramaSaude(),
            filtro.getTipoProduto(),
            filtro.getSiglaSistemaOrigem()
        );
        return response != null ? response.parametros() : List.of();
    }

    public List<EstabelecimentoDTO> buscarEstabelecimentosComFiltro(EstabelecimentoFiltroDTO filtro) {

        String codigoIbgeUf = null;
        
        codigoIbgeUf = verificarUf(filtro.getUf());

        EstabelecimentoResponse response = client.obterEstabelecimentos(
            filtro.getCodigoTipoUnidade(),
            codigoIbgeUf,
            filtro.getCodigoMunicipio(),
            filtro.getStatus(),
            filtro.getEstabelecimentoPossuiCentroCirurgico(),
            filtro.getEstabelecimentoPossuiCentroObstetrico(),
            filtro.getDataAtualizacao(),
            filtro.getLimit(),
            filtro.getOffset()
        );

        return response != null ? response.estabelecimentos() : List.of();
    }

    public EstabelecimentoDTO buscarEstabelecimentoPorCnes(Integer codigoCnes) {
        if (codigoCnes == null) {
            throw new WebApplicationException("O código CNES é obrigatório.", Response.Status.BAD_REQUEST);
        }

        try {
            EstabelecimentoDTO estabelecimento = client.obterEstabelecimentoPorCnes(codigoCnes);
            if (estabelecimento == null) {
                throw new WebApplicationException("Estabelecimento não encontrado para o CNES informado.", Response.Status.NOT_FOUND);
            }
            return estabelecimento;
        } catch (WebApplicationException e) {
            throw e;
        }
    }

    public String verificarUf(String uf) {

        if (uf != null && !uf.isBlank()) {
            EstadoUF estado = EstadoUF.buscarPorSigla(uf);
            return estado.getCodigoIbge();
        }

        return null;
    }
}
