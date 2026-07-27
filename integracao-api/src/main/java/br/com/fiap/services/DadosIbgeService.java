package br.com.fiap.services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.eclipse.microprofile.rest.client.inject.RestClient;

import br.com.fiap.clients.DadosIbgeClient;
import br.com.fiap.dtos.pesquisaibge.PesquisaFiltroDTO;
import br.com.fiap.responses.PesquisaResponse;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class DadosIbgeService {
    
    @Inject
    @RestClient
    DadosIbgeClient client;

    public List<PesquisaResponse> buscarDadosPesquisa(PesquisaFiltroDTO filtro) {
        
        String localidades = "";
        String anos = "";
        String idades = "";
        String domicilios = "";
        String sexos = "";
        List<String> classificacao = new ArrayList<>();
        
        if (filtro.getLocalidades() != null && !filtro.getLocalidades().isEmpty()) {
            String codigosLocalidades = filtro.getLocalidades().stream()
                .map(String::valueOf)
                .collect(Collectors.joining(",", "[", "]"));
    
            localidades = filtro.getNivelGeografico() + codigosLocalidades;
        }
        
        if (filtro.getAno() != null && !filtro.getAno().isEmpty()) {
            anos = filtro.getAno().stream()
                .map(String::valueOf)
                .collect(Collectors.joining("|"));

        }
        
        if (filtro.getGrupoIdades() != null && !filtro.getGrupoIdades().isEmpty()) {
            String codigosIdades = filtro.getGrupoIdades().stream()
                .map(String::valueOf)
                .collect(Collectors.joining(",", "[", "]"));

            idades = "58" + codigosIdades;
            classificacao.add(idades);
        }
        
        if (filtro.getDomicilios() != null && !filtro.getDomicilios().isEmpty()) {

            String codigosDomicilios = filtro.getDomicilios().stream()
                .map(String::valueOf)
                .collect(Collectors.joining(",", "[", "]"));
            
            domicilios = "1" + codigosDomicilios;
            classificacao.add(domicilios);
        }

        if (filtro.getSexo() != null && !filtro.getSexo().isEmpty()) {
            String codigosSexo = filtro.getSexo().stream()
                .map(String::valueOf)
                .collect(Collectors.joining(",", "[", "]"));
    
            sexos = "2" + codigosSexo;
            classificacao.add(sexos);
        }

        List<PesquisaResponse> response = client.obterDadosPesquisa(
            filtro.getAgregador().toString(),
            filtro.getVariavel().toString(),
            anos,
            localidades,
            String.join("|", classificacao)
        );

        return response;
    }
}
