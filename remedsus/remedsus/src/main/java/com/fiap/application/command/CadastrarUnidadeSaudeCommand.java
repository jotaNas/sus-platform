package com.fiap.application.command;

import com.fiap.domain.model.TipoUnidadeSaude;

public record CadastrarUnidadeSaudeCommand(
        String nome,
        TipoUnidadeSaude tipo,
        String municipio,
        String bairro
) {}
