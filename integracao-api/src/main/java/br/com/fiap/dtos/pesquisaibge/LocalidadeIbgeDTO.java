package br.com.fiap.dtos.pesquisaibge;

public record LocalidadeIbgeDTO(
    String id,
    NivelGeograficoDTO nivel,
    String nome
) {}
