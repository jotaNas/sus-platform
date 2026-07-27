package com.fiap.cardioradar.infrastructure.persistence.jpa.mapper;

import com.fiap.cardioradar.domain.MunicipioMonitorado;
import com.fiap.cardioradar.infrastructure.persistence.jpa.entity.MunicipioMonitoradoEntity;

public final class MunicipioMonitoradoJpaMapper {

    private MunicipioMonitoradoJpaMapper() {
    }

    public static MunicipioMonitorado toDomain(
            MunicipioMonitoradoEntity entity
    ) {
        if (entity == null) {
            return null;
        }

        return new MunicipioMonitorado(
                entity.id,
                entity.codigoIbge,
                entity.nome,
                entity.uf,
                entity.ativo,
                entity.criadoEm,
                entity.atualizadoEm
        );
    }

    public static MunicipioMonitoradoEntity toEntity(
            MunicipioMonitorado domain
    ) {
        if (domain == null) {
            return null;
        }

        MunicipioMonitoradoEntity entity =
                new MunicipioMonitoradoEntity();

        entity.id = domain.getId();

        atualizarCampos(domain, entity);

        return entity;
    }

    public static void updateEntity(
            MunicipioMonitorado domain,
            MunicipioMonitoradoEntity entity
    ) {
        if (domain == null) {
            throw new IllegalArgumentException(
                    "Município de domínio é obrigatório."
            );
        }

        if (entity == null) {
            throw new IllegalArgumentException(
                    "Entidade de município é obrigatória."
            );
        }

        atualizarCampos(domain, entity);
    }

    private static void atualizarCampos(
            MunicipioMonitorado domain,
            MunicipioMonitoradoEntity entity
    ) {
        entity.codigoIbge = domain.getCodigoIbge();
        entity.nome = domain.getNome();
        entity.uf = domain.getUf();
        entity.ativo = domain.isAtivo();
        entity.criadoEm = domain.getCriadoEm();
        entity.atualizadoEm = domain.getAtualizadoEm();
    }
}