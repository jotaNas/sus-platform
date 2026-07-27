package com.fiap.infrastructure.persistence.jpa.mapper;

import com.fiap.domain.model.UnidadeSaude;
import com.fiap.infrastructure.persistence.jpa.entity.UnidadeSaudeJPAEntity;

public final class UnidadeSaudeJpaMapper {

    private UnidadeSaudeJpaMapper() {
    }

    public static UnidadeSaude toDomain(UnidadeSaudeJPAEntity entity) {

        UnidadeSaude unidade = new UnidadeSaude(
                entity.getId(),
                entity.getNome(),
                entity.getTipo(),
                entity.getMunicipio(),
                entity.getBairro()
        );

        if (!entity.isAtiva()) {
            unidade.inativar();
        }

        return unidade;
    }

    public static UnidadeSaudeJPAEntity toEntity(UnidadeSaude domain) {

        UnidadeSaudeJPAEntity entity = new UnidadeSaudeJPAEntity();

        entity.setId(domain.getId());
        entity.setNome(domain.getNome());
        entity.setTipo(domain.getTipo());
        entity.setMunicipio(domain.getMunicipio());
        entity.setBairro(domain.getBairro());
        entity.setAtiva(domain.isAtiva());

        return entity;
    }
}