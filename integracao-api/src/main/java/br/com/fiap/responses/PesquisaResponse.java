package br.com.fiap.responses;

import java.util.List;

import br.com.fiap.dtos.pesquisaibge.ResultadoIbgeDTO;

public record PesquisaResponse(
    String id,
    String variavel,
    String unidade,
    List<ResultadoIbgeDTO> resultados
) {}
