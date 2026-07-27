package com.fiap.cardioradar.infrastructure.persistence.jpa.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.UUID;

@Entity
@Table(name = "municipios")
public class MunicipioMonitoradoEntity {

    @Id
    @Column(
            name = "id",
            nullable = false
    )
    public UUID id;

    @Column(
            name = "codigo_ibge",
            nullable = false,
            unique = true,
            length = 7
    )
    public String codigoIbge;

    @Column(
            name = "nome",
            nullable = false,
            length = 150
    )
    public String nome;

    @Column(
            name = "uf",
            nullable = false,
            length = 2
    )
    public String uf;

    @Column(
            name = "ativo",
            nullable = false
    )
    public boolean ativo;

    @Column(
            name = "criado_em",
            nullable = false
    )
    public LocalDateTime criadoEm;

    @Column(
            name = "atualizado_em",
            nullable = false
    )
    public LocalDateTime atualizadoEm;

    public MunicipioMonitoradoEntity() {
    }
}