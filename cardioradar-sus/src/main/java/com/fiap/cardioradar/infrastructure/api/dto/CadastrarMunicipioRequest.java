package com.fiap.cardioradar.infrastructure.api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record CadastrarMunicipioRequest(

        @NotBlank(message = "Código IBGE é obrigatório.")
        @Pattern(
                regexp = "\\d{7}",
                message = "Código IBGE deve possuir exatamente 7 números."
        )
        String codigoIbge,

        @NotBlank(message = "Nome do município é obrigatório.")
        @Size(
                min = 2,
                max = 150,
                message = "Nome do município deve possuir entre 2 e 150 caracteres."
        )
        String nome,

        @NotBlank(message = "UF é obrigatória.")
        @Pattern(
                regexp = "[A-Za-z]{2}",
                message = "UF deve possuir exatamente duas letras."
        )
        String uf
) {
}