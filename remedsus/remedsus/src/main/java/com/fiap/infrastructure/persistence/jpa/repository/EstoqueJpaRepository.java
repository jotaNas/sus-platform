package com.fiap.infrastructure.persistence.jpa.repository;
import com.fiap.infrastructure.persistence.jpa.entity.EstoqueJPAEntity;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.Optional;

@ApplicationScoped
public class EstoqueJpaRepository implements PanacheRepositoryBase<EstoqueJPAEntity, String> {

    public Optional<EstoqueJPAEntity> findByUnidadeAndMedicamento(
            String unidadeId,
            String medicamentoId
    ) {
        return find(
                "unidadeId = ?1 and medicamentoId = ?2",
                unidadeId,
                medicamentoId
        ).firstResultOptional();
    }
}
