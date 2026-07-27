package com.fiap.infrastructure.api.dto;

import com.fiap.domain.model.TipoUnidadeSaude;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CadastrarUnidadeSaudeRequest(
        @NotBlank String nome,
        @NotNull TipoUnidadeSaude tipo,
        @NotBlank String municipio,
        @NotBlank String bairro
) {}
