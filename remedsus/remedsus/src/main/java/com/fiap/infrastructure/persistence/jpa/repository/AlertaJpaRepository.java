package com.fiap.infrastructure.persistence.jpa.repository;

import com.fiap.domain.vo.StatusAlerta;
import com.fiap.domain.vo.TipoAlerta;
import com.fiap.infrastructure.persistence.jpa.entity.AlertaJPAEntity;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class AlertaJpaRepository implements PanacheRepositoryBase<AlertaJPAEntity, String> {

    public Optional<AlertaJPAEntity> findAbertoPorTipoUnidadeMedicamento(
            TipoAlerta tipo,
            String unidadeId,
            String medicamentoId
    ) {
        return find(
                "tipo = ?1 and unidadeId = ?2 and medicamentoId = ?3 and status = ?4",
                tipo,
                unidadeId,
                medicamentoId,
                StatusAlerta.ABERTO
        ).firstResultOptional();
    }

    public List<AlertaJPAEntity> findByStatus(StatusAlerta status) {
        return list("status = ?1 order by criadoEm desc", status);
    }

    public List<AlertaJPAEntity> findByEstoqueId(String estoqueId) {
        return list("estoqueId = ?1 order by criadoEm desc", estoqueId);
    }
}
