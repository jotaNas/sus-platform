package com.fiap.domain.model;

import java.time.LocalDate;

public class Lote {

    private final String id;
    private final String medicamentoId;
    private final String numero;
    private final LocalDate validade;
    private int quantidadeAtual;

    public Lote(
            String id,
            String medicamentoId,
            String numero,
            LocalDate validade,
            int quantidadeAtual
    ) {
        if (validade.isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("Lote não pode estar vencido.");
        }

        this.id = id;
        this.medicamentoId = medicamentoId;
        this.numero = numero;
        this.validade = validade;
        this.quantidadeAtual = quantidadeAtual;
    }

    public int baixar(int quantidadeSolicitada) {
        int quantidadeBaixada = Math.min(this.quantidadeAtual, quantidadeSolicitada);
        this.quantidadeAtual -= quantidadeBaixada;
        return quantidadeBaixada;
    }

    public boolean estaVencido() {
        return validade.isBefore(LocalDate.now());
    }

    public boolean estaProximoDoVencimento() {
        return validade.isBefore(LocalDate.now().plusDays(30));
    }

    public String getId() {
        return id;
    }

    public LocalDate getValidade() {
        return validade;
    }

    public int getQuantidadeAtual() {
        return quantidadeAtual;
    }

    public String getNumero() {
        return numero;
    }
}
