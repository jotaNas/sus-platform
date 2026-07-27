package com.fiap.cardioradar.domain;


import com.fiap.cardioradar.domain.vo.NivelRisco;

import java.time.LocalDateTime;
import java.util.UUID;

public class RiscoCardiovascular {

    private final UUID id;
    private final UUID municipioId;
    private final UUID indicadorId;
    private final UUID pressaoMedicamentoId;
    private final String medicamento;
    private final Integer indice;
    private final NivelRisco nivel;
    private final String justificativa;
    private final LocalDateTime calculatedAt;

    public RiscoCardiovascular(
            UUID id,
            UUID municipioId,
            UUID indicadorId,
            UUID pressaoMedicamentoId,
            String medicamento,
            Integer indice,
            NivelRisco nivel,
            String justificativa,
            LocalDateTime calculatedAt
    ) {
        this.id = id;
        this.municipioId = municipioId;
        this.indicadorId = indicadorId;
        this.pressaoMedicamentoId = pressaoMedicamentoId;
        this.medicamento = medicamento;
        this.indice = indice;
        this.nivel = nivel;
        this.justificativa = justificativa;
        this.calculatedAt = calculatedAt;
    }

    public boolean exigeAlerta() {
        return nivel == NivelRisco.ALTO_RISCO || nivel == NivelRisco.CRITICO;
    }

    public UUID getId() {
        return id;
    }

    public UUID getMunicipioId() {
        return municipioId;
    }

    public Integer getIndice() {
        return indice;
    }

    public NivelRisco getNivel() {
        return nivel;
    }

    public String getMedicamento() {
        return medicamento;
    }

    public String getJustificativa() {
        return justificativa;
    }

    public LocalDateTime getCalculatedAt() {
        return calculatedAt;
    }
}
