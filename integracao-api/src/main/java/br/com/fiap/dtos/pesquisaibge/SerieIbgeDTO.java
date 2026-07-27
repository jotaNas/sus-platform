package br.com.fiap.dtos.pesquisaibge;

import java.util.Map;

public record SerieIbgeDTO(
    LocalidadeIbgeDTO localidade,
    Map<String, String> serie
) {}
