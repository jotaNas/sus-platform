package com.fiap.cardioradar.domain;

import com.fiap.cardioradar.domain.vo.NivelRisco;
import com.fiap.cardioradar.domain.vo.TendenciaRisco;

import java.time.LocalDateTime;
import java.time.YearMonth;
import java.util.UUID;

public class RiscoMunicipal {

    private final UUID id;
    private final UUID municipioId;
    private final UUID indicadorId;
    private final YearMonth competencia;
    private final double indicePressaoCardiovascular;
    private final NivelRisco nivel;
    private final TendenciaRisco tendencia;
    private final LocalDateTime calculadoEm;

    public RiscoMunicipal(
            UUID id,
            UUID municipioId,
            UUID indicadorId,
            YearMonth competencia,
            double indicePressaoCardiovascular,
            NivelRisco nivel,
            TendenciaRisco tendencia,
            LocalDateTime calculadoEm
    ) {
        validarTexto(String.valueOf(id), "Id do risco é obrigatório.");
        validarTexto(String.valueOf(municipioId), "Município é obrigatório.");
        validarTexto(String.valueOf(indicadorId), "Indicador é obrigatório.");

        if (competencia == null) {
            throw new IllegalArgumentException("Competência é obrigatória.");
        }

        if (indicePressaoCardiovascular < 0) {
            throw new IllegalArgumentException(
                    "Índice de pressão cardiovascular não pode ser negativo."
            );
        }

        if (nivel == null) {
            throw new IllegalArgumentException("Nível de risco é obrigatório.");
        }

        if (tendencia == null) {
            throw new IllegalArgumentException("Tendência é obrigatória.");
        }

        this.id = id;
        this.municipioId = municipioId;
        this.indicadorId = indicadorId;
        this.competencia = competencia;
        this.indicePressaoCardiovascular = indicePressaoCardiovascular;
        this.nivel = nivel;
        this.tendencia = tendencia;
        this.calculadoEm = calculadoEm == null
                ? LocalDateTime.now()
                : calculadoEm;
    }

    public boolean exigeAlerta() {
        return nivel == NivelRisco.ALTO_RISCO
                || nivel == NivelRisco.CRITICO;
    }

    public boolean indicaPressaoCrescente() {
        return tendencia == TendenciaRisco.CRESCIMENTO;
    }

    private void validarTexto(String valor, String mensagem) {
        if (valor == null || valor.isBlank()) {
            throw new IllegalArgumentException(mensagem);
        }
    }

    public UUID getId() {
        return id;
    }

    public UUID getMunicipioId() {
        return municipioId;
    }

    public UUID getIndicadorId() {
        return indicadorId;
    }

    public YearMonth getCompetencia() {
        return competencia;
    }

    public double getIndicePressaoCardiovascular() {
        return indicePressaoCardiovascular;
    }

    public NivelRisco getNivel() {
        return nivel;
    }

    public TendenciaRisco getTendencia() {
        return tendencia;
    }

    public LocalDateTime getCalculadoEm() {
        return calculadoEm;
    }
}