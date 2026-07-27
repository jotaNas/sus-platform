package com.fiap.infrastructure.persistence.jpa.repository;


import com.fiap.infrastructure.persistence.jpa.entity.UnidadeSaudeJPAEntity;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.Optional;

@ApplicationScoped
public class UnidadeSaudeJpaRepository implements PanacheRepositoryBase<UnidadeSaudeJPAEntity, String> {

    public Optional<UnidadeSaudeJPAEntity> findByIdValue(String id) {
        return findByIdOptional(id);
    }
}
