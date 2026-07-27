package com.fiap.infrastructure.persistence.jpa.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "lotes")
public class LoteJPAEntity extends PanacheEntityBase {


    @Id
    public String id;

    @Column(name = "medicamento_id", nullable = false)
    public String medicamentoId;

    @Column(nullable = false)
    public String numero;

    @Column(nullable = false)
    public LocalDate validade;

    @Column(name = "quantidade_atual", nullable = false)
    public int quantidadeAtual;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "estoque_id", nullable = false)
    public EstoqueJPAEntity estoque;
}