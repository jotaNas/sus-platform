package br.com.fiap.responses;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.fiap.dtos.estoquemedicamentos.EstoqueMedicamentosDTO;

public record EstoqueMedicamentosResponse(
     @JsonProperty("parametros") List<EstoqueMedicamentosDTO> parametros
) {}
