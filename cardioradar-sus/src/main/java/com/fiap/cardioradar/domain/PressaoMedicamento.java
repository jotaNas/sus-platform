package com.fiap.cardioradar.domain;

import java.util.UUID;

public class PressaoMedicamento {

    private final UUID id;
    private final UUID municipioId;
    private final String medicamento;
    private final Integer consumoMensalMedio;
    private final Integer estoqueAtual;
    private final Integer diasCobertura;

    public PressaoMedicamento(
            UUID id,
            UUID municipioId,
            String medicamento,
            Integer consumoMensalMedio,
            Integer estoqueAtual
    ) {
        this.id = id;
        this.municipioId = municipioId;
        this.medicamento = medicamento;
        this.consumoMensalMedio = consumoMensalMedio;
        this.estoqueAtual = estoqueAtual;
        this.diasCobertura = calcularDiasCobertura(estoqueAtual, consumoMensalMedio);
    }

    private Integer calcularDiasCobertura(Integer estoqueAtual, Integer consumoMensalMedio) {
        if (consumoMensalMedio == null || consumoMensalMedio == 0) {
            return 999;
        }

        double consumoDiario = consumoMensalMedio / 30.0;
        return (int) Math.floor(estoqueAtual / consumoDiario);
    }

    public boolean estaComBaixaCobertura() {
        return diasCobertura <= 30;
    }

    public UUID getId() {
        return id;
    }

    public UUID getMunicipioId() {
        return municipioId;
    }

    public String getMedicamento() {
        return medicamento;
    }

    public Integer getConsumoMensalMedio() {
        return consumoMensalMedio;
    }

    public Integer getEstoqueAtual() {
        return estoqueAtual;
    }

    public Integer getDiasCobertura() {
        return diasCobertura;
    }
}
