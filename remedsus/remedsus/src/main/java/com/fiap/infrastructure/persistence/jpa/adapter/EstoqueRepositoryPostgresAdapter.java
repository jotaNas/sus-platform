package com.fiap.infrastructure.persistence.jpa.adapter;

import com.fiap.domain.dataaccess.EstoqueDataAccess;
import com.fiap.domain.model.Estoque;
import com.fiap.infrastructure.persistence.jpa.entity.EstoqueJPAEntity;
import com.fiap.infrastructure.persistence.jpa.mapper.EstoqueJpaMapper;
import com.fiap.infrastructure.persistence.jpa.repository.EstoqueJpaRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

import java.util.Optional;

@ApplicationScoped
public class EstoqueRepositoryPostgresAdapter implements EstoqueDataAccess {

    private final EstoqueJpaRepository repository;

    public EstoqueRepositoryPostgresAdapter(EstoqueJpaRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<Estoque> buscarPorUnidadeEMedicamento(
            String unidadeId,
            String medicamentoId
    ) {
        return repository.findByUnidadeAndMedicamento(unidadeId, medicamentoId)
                .map(EstoqueJpaMapper::toDomain);
    }

    @Override
    @Transactional
    public void salvar(Estoque estoque) {
        repository.findByIdOptional(estoque.getId())
                .ifPresentOrElse(
                        existente -> {
                            repository.getEntityManager().remove(existente);
                            repository.getEntityManager().flush();

                            EstoqueJPAEntity nova = EstoqueJpaMapper.toEntity(estoque);
                            repository.persist(nova);
                        },
                        () -> {
                            EstoqueJPAEntity nova = EstoqueJpaMapper.toEntity(estoque);
                            repository.persist(nova);
                        }
                );
    }
}