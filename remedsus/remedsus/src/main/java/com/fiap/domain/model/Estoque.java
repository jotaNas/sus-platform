package com.fiap.domain.model;

import com.fiap.domain.event.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Estoque {

    private final String id;
    private final String unidadeId;
    private final String medicamentoId;
    private int saldoAtual;
    private int estoqueMinimo;
    private final List<Lote> lotes = new ArrayList<>();
    private final List<DomainEvent> eventos = new ArrayList<>();

    public Estoque(
            String id,
            String unidadeId,
            String medicamentoId,
            int estoqueMinimo
    ) {
        this.id = id;
        this.unidadeId = unidadeId;
        this.medicamentoId = medicamentoId;
        this.estoqueMinimo = estoqueMinimo;
        this.saldoAtual = 0;
    }

    public void registrarEntrada(String loteId, String numeroLote, LocalDate validade, int quantidade) {
        if (quantidade <= 0) {
            throw new IllegalArgumentException("Quantidade de entrada deve ser maior que zero.");
        }

        Lote lote = new Lote(
                loteId,
                medicamentoId,
                numeroLote,
                validade,
                quantidade
        );

        this.lotes.add(lote);
        this.saldoAtual += quantidade;

        registrarEvento(new LoteRecebidoEvent(
                id,
                unidadeId,
                medicamentoId,
                loteId,
                quantidade,
                validade
        ));

        registrarEvento(new EstoqueAtualizadoEvent(
                unidadeId,
                medicamentoId,
                saldoAtual
        ));

        verificarRegrasDeEstoque();
    }

    public void dispensar(int quantidade) {
        if (quantidade <= 0) {
            throw new IllegalArgumentException("Quantidade deve ser maior que zero.");
        }

        if (quantidade > saldoAtual) {
            throw new IllegalStateException("Saldo insuficiente para dispensação.");
        }

        int restante = quantidade;

        List<Lote> lotesOrdenados = lotes.stream()
                .filter(lote -> lote.getQuantidadeAtual() > 0)
                .filter(lote -> !lote.estaVencido())
                .sorted(Comparator.comparing(Lote::getValidade))
                .toList();

        for (Lote lote : lotesOrdenados) {
            if (restante == 0) break;

            int quantidadeConsumida = lote.baixar(restante);
            restante -= quantidadeConsumida;
        }

        if (restante > 0) {
            throw new IllegalStateException("Não há lote válido suficiente para dispensação.");
        }

        this.saldoAtual -= quantidade;

        registrarEvento(new MedicamentoDispensadoEvent(
                id,
                unidadeId,
                medicamentoId,
                quantidade
        ));

        registrarEvento(new EstoqueAtualizadoEvent(
                unidadeId,
                medicamentoId,
                saldoAtual
        ));

        verificarRegrasDeEstoque();
    }

    private void verificarRegrasDeEstoque() {
        if (saldoAtual == 0) {
            registrarEvento(new MedicamentoZeradoEvent(
                    unidadeId,
                    medicamentoId
            ));
            return;
        }

        if (saldoAtual <= estoqueMinimo) {
            registrarEvento(new EstoqueMinimoAtingidoEvent(
                    unidadeId,
                    medicamentoId,
                    saldoAtual,
                    estoqueMinimo
            ));
        }

        lotes.stream()
                .filter(Lote::estaProximoDoVencimento)
                .forEach(lote -> registrarEvento(new MedicamentoProximoDoVencimentoEvent(
                        unidadeId,
                        medicamentoId,
                        lote.getId(),
                        lote.getValidade()
                )));
    }

    private void registrarEvento(DomainEvent event) {
        this.eventos.add(event);
    }

    public List<DomainEvent> pullEvents() {
        List<DomainEvent> copia = new ArrayList<>(eventos);
        eventos.clear();
        return copia;
    }

    public String getId() {
        return id;
    }

    public int getSaldoAtual() {
        return saldoAtual;
    }

    public String getUnidadeId() {
        return unidadeId;
    }

    public String getMedicamentoId() {
        return medicamentoId;
    }

    public int getEstoqueMinimo() {
        return estoqueMinimo;
    }

    public List<Lote> getLotes() {
        return Collections.unmodifiableList(lotes);
    }

    public void restaurarSaldo(int saldoAtual) {
        if (saldoAtual < 0) {
            throw new IllegalArgumentException("Saldo não pode ser negativo.");
        }

        this.saldoAtual = saldoAtual;
    }

    public void restaurarLote(Lote lote) {
        this.lotes.add(lote);
    }

    public void limparEventos() {
        this.eventos.clear();
    }
}
