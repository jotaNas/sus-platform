package com.fiap.cardioradar.infrastructure.persistence.jpa.mapper;

import com.fiap.cardioradar.domain.IndicadorCardiovascular;
import com.fiap.cardioradar.infrastructure.persistence.jpa.entity.IndicadorCardiovascularEntity;

import java.time.YearMonth;

public final class IndicadorCardiovascularJpaMapper {

    private IndicadorCardiovascularJpaMapper() {
    }

    public static IndicadorCardiovascular toDomain(
            IndicadorCardiovascularEntity entity
    ) {
        if (entity == null) {
            return null;
        }

        return new IndicadorCardiovascular(
                entity.id,
                entity.municipioId,
                YearMonth.parse(entity.competencia),
                entity.populacaoEstimada,
                entity.populacaoIdosa,
                entity.atendimentosHipertensao,
                entity.atendimentosDiabetes,
                entity.internacoesCardiovasculares,
                entity.obitosCardiovasculares,
                entity.procedimentosCardiovasculares,
                entity.fonte,
                entity.registradoEm
        );
    }

    public static IndicadorCardiovascularEntity toEntity(
            IndicadorCardiovascular domain
    ) {
        if (domain == null) {
            return null;
        }

        IndicadorCardiovascularEntity entity =
                new IndicadorCardiovascularEntity();

        mapearCampos(domain, entity);

        return entity;
    }

    public static void updateEntity(
            IndicadorCardiovascular domain,
            IndicadorCardiovascularEntity entity
    ) {
        if (domain == null) {
            throw new IllegalArgumentException(
                    "Indicador cardiovascular é obrigatório."
            );
        }

        if (entity == null) {
            throw new IllegalArgumentException(
                    "Entidade de indicador cardiovascular é obrigatória."
            );
        }

        mapearCampos(domain, entity);
    }

    private static void mapearCampos(
            IndicadorCardiovascular domain,
            IndicadorCardiovascularEntity entity
    ) {
        entity.id = domain.getId();
        entity.municipioId = domain.getMunicipioId();
        entity.competencia = domain.getCompetencia().toString();

        entity.populacaoEstimada =
                domain.getPopulacaoEstimada();

        entity.populacaoIdosa =
                domain.getPopulacaoIdosa();

        entity.atendimentosHipertensao =
                domain.getAtendimentosHipertensao();

        entity.atendimentosDiabetes =
                domain.getAtendimentosDiabetes();

        entity.internacoesCardiovasculares =
                domain.getInternacoesCardiovasculares();

        entity.obitosCardiovasculares =
                domain.getObitosCardiovasculares();

        entity.procedimentosCardiovasculares =
                domain.getProcedimentosCardiovasculares();

        entity.fonte = domain.getFonte();
        entity.registradoEm = domain.getRegistradoEm();
    }
}