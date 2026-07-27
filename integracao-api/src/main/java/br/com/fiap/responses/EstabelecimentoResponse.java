package br.com.fiap.responses;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.fiap.dtos.estabelecimento.EstabelecimentoDTO;

public record EstabelecimentoResponse(
    @JsonProperty("estabelecimentos") List<EstabelecimentoDTO> estabelecimentos
) {}
