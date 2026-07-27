package com.fiap.cardioradar.infrastructure.persistence.jpa.entity;

import com.fiap.cardioradar.domain.vo.NivelRisco;
import com.fiap.cardioradar.domain.vo.TendenciaRisco;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(
        name = "riscos_municipais",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "uk_risco_municipio_competencia",
                        columnNames = {
                                "municipio_id",
                                "competencia"
                        }
                ),
                @UniqueConstraint(
                        name = "uk_risco_indicador",
                        columnNames = "indicador_id"
                )
        }
)
public class RiscoMunicipalEntity {

    @Id
    @Column(
            name = "id",
            nullable = false
    )
    public UUID id;

    @Column(
            name = "municipio_id",
            nullable = false
    )
    public UUID municipioId;

    @Column(
            name = "indicador_id",
            nullable = false
    )
    public UUID indicadorId;

    @Column(
            name = "competencia",
            nullable = false,
            length = 7
    )
    public String competencia;

    @Column(
            name = "indice_pressao_cardiovascular",
            nullable = false,
            precision = 10,
            scale = 2
    )
    public BigDecimal indicePressaoCardiovascular;

    @Enumerated(EnumType.STRING)
    @Column(
            name = "nivel",
            nullable = false,
            length = 20
    )
    public NivelRisco nivel;

    @Enumerated(EnumType.STRING)
    @Column(
            name = "tendencia",
            nullable = false,
            length = 20
    )
    public TendenciaRisco tendencia;

    @Column(
            name = "calculado_em",
            nullable = false
    )
    public LocalDateTime calculadoEm;
}