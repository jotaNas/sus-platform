package br.com.fiap.dtos.pesquisaibge;

import java.util.List;

public record ResultadoIbgeDTO(
    List<ClassificacaoIbgeDTO> classificacoes,
    List<SerieIbgeDTO> series
) {}
