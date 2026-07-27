package com.fiap.cardioradar.domain;

import com.fiap.cardioradar.domain.vo.NivelRisco;

import java.time.LocalDateTime;
import java.util.UUID;

public class AlertaPreventivo {

    private final UUID id;
    private final UUID municipioId;
    private final UUID riscoId;
    private final String medicamento;
    private final NivelRisco nivel;
    private final String mensagem;
    private final LocalDateTime createdAt;

    public AlertaPreventivo(
            UUID id,
            UUID municipioId,
            UUID riscoId,
            String medicamento,
            NivelRisco nivel,
            String mensagem,
            LocalDateTime createdAt
    ) {
        this.id = id;
        this.municipioId = municipioId;
        this.riscoId = riscoId;
        this.medicamento = medicamento;
        this.nivel = nivel;
        this.mensagem = mensagem;
        this.createdAt = createdAt;
    }
}
