package br.com.fiap.dtos.erro;

import java.time.LocalDateTime;

public record ErroRespostaDTO(
    String mensagem,
    int status,
    LocalDateTime timestamp
) {}
