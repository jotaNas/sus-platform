package com.fiap.infrastructure.persistence.jpa.repository;

import com.fiap.infrastructure.persistence.jpa.entity.MovimentacaoEstoqueJPAEntity;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;

@ApplicationScoped
public class MovimentacaoEstoqueJpaRepository
        implements PanacheRepositoryBase<MovimentacaoEstoqueJPAEntity, String> {

    public List<MovimentacaoEstoqueJPAEntity> findByEstoqueId(String estoqueId) {
        return list("estoqueId = ?1 order by realizadaEm desc", estoqueId);
    }
}
