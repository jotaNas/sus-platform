package com.fiap.cardioradar.application.command;

public record CadastrarMunicipioCommand(
        String codigoIbge,
        String nome,
        String uf
) {
}
