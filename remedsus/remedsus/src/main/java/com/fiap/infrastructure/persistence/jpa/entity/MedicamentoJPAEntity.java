package com.fiap.infrastructure.persistence.jpa.entity;


import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;

@Entity
@Table(name = "medicamentos")
public class MedicamentoJPAEntity extends PanacheEntityBase {

    @Id
    public String id;

    @Column(nullable = false)
    public String nome;

    @Column(name = "principio_ativo", nullable = false)
    public String principioAtivo;

    @Column(nullable = false)
    public String apresentacao;

    @Column(nullable = false)
    public boolean ativo;
}
