package com.fiap.cardioradar.infrastructure.persistence.jpa.mapper;

import com.fiap.cardioradar.domain.RiscoMunicipal;
import com.fiap.cardioradar.infrastructure.persistence.jpa.entity.RiscoMunicipalEntity;

import java.math.BigDecimal;
import java.time.YearMonth;

public final class RiscoMunicipalJpaMapper {

    private RiscoMunicipalJpaMapper() {
    }

    public static RiscoMunicipal toDomain(
            RiscoMunicipalEntity entity
    ) {
        if (entity == null) {
            return null;
        }

        return new RiscoMunicipal(
                entity.id,
                entity.municipioId,
                entity.indicadorId,
                YearMonth.parse(entity.competencia),
                entity.indicePressaoCardiovascular.doubleValue(),
                entity.nivel,
                entity.tendencia,
                entity.calculadoEm
        );
    }

    public static RiscoMunicipalEntity toEntity(
            RiscoMunicipal domain
    ) {
        if (domain == null) {
            return null;
        }

        RiscoMunicipalEntity entity = new RiscoMunicipalEntity();

        entity.id = domain.getId();
        entity.municipioId = domain.getMunicipioId();
        entity.indicadorId = domain.getIndicadorId();
        entity.competencia = domain.getCompetencia().toString();
        entity.indicePressaoCardiovascular =
                BigDecimal.valueOf(
                        domain.getIndicePressaoCardiovascular()
                );
        entity.nivel = domain.getNivel();
        entity.tendencia = domain.getTendencia();
        entity.calculadoEm = domain.getCalculadoEm();

        return entity;
    }

    public static void updateEntity(
            RiscoMunicipal domain,
            RiscoMunicipalEntity entity
    ) {
        entity.municipioId = domain.getMunicipioId();
        entity.indicadorId = domain.getIndicadorId();
        entity.competencia = domain.getCompetencia().toString();
        entity.indicePressaoCardiovascular =
                BigDecimal.valueOf(
                        domain.getIndicePressaoCardiovascular()
                );
        entity.nivel = domain.getNivel();
        entity.tendencia = domain.getTendencia();
        entity.calculadoEm = domain.getCalculadoEm();
    }
}
