package com.fiap.infrastructure.persistence.jpa.adapter;

import com.fiap.domain.dataaccess.MovimentacaoEstoqueDataAccess;
import com.fiap.domain.model.MovimentacaoEstoque;
import com.fiap.infrastructure.persistence.jpa.mapper.MovimentacaoEstoqueJpaMapper;
import com.fiap.infrastructure.persistence.jpa.repository.MovimentacaoEstoqueJpaRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;

@ApplicationScoped
public class MovimentacaoEstoquePostgresAdapter implements MovimentacaoEstoqueDataAccess {

    private final MovimentacaoEstoqueJpaRepository repository;

    public MovimentacaoEstoquePostgresAdapter(MovimentacaoEstoqueJpaRepository repository) {
        this.repository = repository;
    }

    @Override
    public void salvar(MovimentacaoEstoque movimentacao) {
        repository.persist(MovimentacaoEstoqueJpaMapper.toEntity(movimentacao));
    }

    @Override
    public List<MovimentacaoEstoque> listarPorEstoqueId(String estoqueId) {
        return repository.findByEstoqueId(estoqueId)
                .stream()
                .map(MovimentacaoEstoqueJpaMapper::toDomain)
                .toList();
    }
}
