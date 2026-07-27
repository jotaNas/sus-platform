package com.fiap.infrastructure.persistence.jpa.adapter;


import com.fiap.domain.dataaccess.UnidadeSaudeDataAccess;
import com.fiap.domain.model.UnidadeSaude;
import com.fiap.infrastructure.persistence.jpa.entity.UnidadeSaudeJPAEntity;
import com.fiap.infrastructure.persistence.jpa.mapper.UnidadeSaudeJpaMapper;
import com.fiap.infrastructure.persistence.jpa.repository.UnidadeSaudeJpaRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.Optional;

@ApplicationScoped
public class UnidadeSaudeRepositoryPostgresAdapter implements UnidadeSaudeDataAccess {

    private final UnidadeSaudeJpaRepository repository;

    public UnidadeSaudeRepositoryPostgresAdapter(UnidadeSaudeJpaRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<UnidadeSaude> buscarPorId(String id) {
        return repository.findByIdValue(id)
                .map(UnidadeSaudeJpaMapper::toDomain);
    }

    @Override
    public void salvar(UnidadeSaude unidade) {
        UnidadeSaudeJPAEntity entity = UnidadeSaudeJpaMapper.toEntity(unidade);
        repository.getEntityManager().merge(entity);
    }
}
