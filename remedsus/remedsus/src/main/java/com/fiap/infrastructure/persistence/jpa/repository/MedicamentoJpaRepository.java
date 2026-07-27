package com.fiap.infrastructure.persistence.jpa.repository;

import com.fiap.infrastructure.persistence.jpa.entity.MedicamentoJPAEntity;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.Optional;

@ApplicationScoped
public class MedicamentoJpaRepository implements PanacheRepositoryBase<MedicamentoJPAEntity, String> {

    public Optional<MedicamentoJPAEntity> findByIdValue(String id) {
        return findByIdOptional(id);
    }

    public boolean existsByNomeAndApresentacao(String nome, String apresentacao) {
        return find("nome = ?1 and apresentacao = ?2", nome, apresentacao)
                .firstResultOptional()
                .isPresent();
    }
}
