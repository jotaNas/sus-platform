package com.fiap.cardioradar.infrastructure.persistence.jpa.entity;

import com.fiap.cardioradar.domain.vo.FonteDado;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.LocalDateTime;

import java.util.UUID;

@Entity
@Table(name = "indicadores_cardiovasculares")
public class IndicadorCardiovascularEntity {

    @Id
    @Column(name = "id", nullable = false)
    public UUID id;

    @Column(name = "municipio_id", nullable = false)
    public UUID municipioId;

    @Column(name = "competencia", nullable = false, length = 7)
    public String competencia;

    @Column(name = "populacao_estimada", nullable = false)
    public int populacaoEstimada;

    @Column(name = "populacao_idosa", nullable = false)
    public int populacaoIdosa;

    @Column(name = "atendimentos_hipertensao", nullable = false)
    public int atendimentosHipertensao;

    @Column(name = "atendimentos_diabetes", nullable = false)
    public int atendimentosDiabetes;

    @Column(name = "internacoes_cardiovasculares", nullable = false)
    public int internacoesCardiovasculares;

    @Column(name = "obitos_cardiovasculares", nullable = false)
    public int obitosCardiovasculares;

    @Column(name = "procedimentos_cardiovasculares", nullable = false)
    public int procedimentosCardiovasculares;

    @Enumerated(EnumType.STRING)
    @Column(name = "fonte", nullable = false, length = 30)
    public FonteDado fonte;

    @Column(name = "registrado_em", nullable = false)
    public LocalDateTime registradoEm;

    public IndicadorCardiovascularEntity() {
    }
}