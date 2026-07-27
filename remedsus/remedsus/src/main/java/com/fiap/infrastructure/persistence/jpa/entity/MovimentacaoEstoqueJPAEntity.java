package com.fiap.infrastructure.persistence.jpa.entity;

import com.fiap.domain.vo.TipoMovimentacaoEstoque;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "movimentacoes_estoque")
public class MovimentacaoEstoqueJPAEntity extends PanacheEntityBase {

    @Id
    public String id;

    @Column(name = "estoque_id")
    public String estoqueId;

    @Column(name = "unidade_id", nullable = false)
    public String unidadeId;

    @Column(name = "medicamento_id", nullable = false)
    public String medicamentoId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    public TipoMovimentacaoEstoque tipo;

    @Column(nullable = false)
    public int quantidade;

    @Column(name = "realizada_em", nullable = false)
    public LocalDateTime realizadaEm;

    @Column(name = "lote_id")
    public String loteId;

    @Column(name = "origem_evento")
    public String origemEvento;
}
