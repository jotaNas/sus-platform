package com.fiap.infrastructure.persistence.jpa.adapter;

import com.fiap.domain.dataaccess.MedicamentoDataAccess;
import com.fiap.domain.model.Medicamento;
import com.fiap.infrastructure.persistence.jpa.entity.MedicamentoJPAEntity;
import com.fiap.infrastructure.persistence.jpa.mapper.MedicamentoJpaMapper;
import com.fiap.infrastructure.persistence.jpa.repository.MedicamentoJpaRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.Optional;

@ApplicationScoped
public class MedicamentoRepositoryPostgresAdapter implements MedicamentoDataAccess {

    private final MedicamentoJpaRepository repository;

    public MedicamentoRepositoryPostgresAdapter(MedicamentoJpaRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<Medicamento> buscarPorId(String id) {
        return repository.findByIdValue(id)
                .map(MedicamentoJpaMapper::toDomain);
    }

    @Override
    public boolean existePorNomeEApresentacao(String nome, String apresentacao) {
        return repository.existsByNomeAndApresentacao(nome, apresentacao);
    }

    @Override
    public void salvar(Medicamento medicamento) {
        MedicamentoJPAEntity entity = MedicamentoJpaMapper.toEntity(medicamento);
        repository.getEntityManager().merge(entity);
    }
}
