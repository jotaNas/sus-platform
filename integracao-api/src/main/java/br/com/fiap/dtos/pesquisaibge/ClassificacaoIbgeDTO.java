package br.com.fiap.dtos.pesquisaibge;

import java.util.Map;

public record ClassificacaoIbgeDTO(
    String id,
    String nome,
    Map<String, String> categoria
) {}
