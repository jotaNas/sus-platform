package com.fiap.infrastructure.persistence.jpa.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "estoques")
public class EstoqueJPAEntity extends PanacheEntityBase {

    @Id
    public String id;

    @Column(name = "unidade_id", nullable = false)
    public String unidadeId;

    @Column(name = "medicamento_id", nullable = false)
    public String medicamentoId;

    @Column(name = "saldo_atual", nullable = false)
    public int saldoAtual;

    @Column(name = "estoque_minimo", nullable = false)
    public int estoqueMinimo;

    @OneToMany(
            mappedBy = "estoque",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.EAGER
    )
    public List<LoteJPAEntity> lotes = new ArrayList<>();
}
