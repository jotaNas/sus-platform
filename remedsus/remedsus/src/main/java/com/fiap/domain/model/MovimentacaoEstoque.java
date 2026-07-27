package com.fiap.domain.model;

import com.fiap.domain.vo.TipoMovimentacaoEstoque;

import java.time.LocalDateTime;

public class MovimentacaoEstoque {

    private final String id;
    private final String estoqueId;
    private final String unidadeId;
    private final String medicamentoId;
    private final TipoMovimentacaoEstoque tipo;
    private final int quantidade;
    private final LocalDateTime realizadaEm;
    private final String loteId;
    private final String origemEvento;

    public MovimentacaoEstoque(
            String id,
            String estoqueId,
            String unidadeId,
            String medicamentoId,
            TipoMovimentacaoEstoque tipo,
            int quantidade,
            LocalDateTime realizadaEm,
            String loteId,
            String origemEvento
    ) {
        if (id == null || id.isBlank()) throw new IllegalArgumentException("Id é obrigatório.");
        if (unidadeId == null || unidadeId.isBlank()) throw new IllegalArgumentException("Unidade é obrigatória.");
        if (medicamentoId == null || medicamentoId.isBlank()) throw new IllegalArgumentException("Medicamento é obrigatório.");
        if (tipo == null) throw new IllegalArgumentException("Tipo da movimentação é obrigatório.");
        if (quantidade <= 0) throw new IllegalArgumentException("Quantidade deve ser maior que zero.");

        this.id = id;
        this.estoqueId = estoqueId;
        this.unidadeId = unidadeId;
        this.medicamentoId = medicamentoId;
        this.tipo = tipo;
        this.quantidade = quantidade;
        this.realizadaEm = realizadaEm == null ? LocalDateTime.now() : realizadaEm;
        this.loteId = loteId;
        this.origemEvento = origemEvento;
    }

    public String getId() { return id; }
    public String getEstoqueId() { return estoqueId; }
    public String getUnidadeId() { return unidadeId; }
    public String getMedicamentoId() { return medicamentoId; }
    public TipoMovimentacaoEstoque getTipo() { return tipo; }
    public int getQuantidade() { return quantidade; }
    public LocalDateTime getRealizadaEm() { return realizadaEm; }
    public String getLoteId() { return loteId; }
    public String getOrigemEvento() { return origemEvento; }
}
