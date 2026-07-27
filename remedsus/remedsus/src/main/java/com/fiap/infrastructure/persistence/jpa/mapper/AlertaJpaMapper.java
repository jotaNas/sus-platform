package com.fiap.infrastructure.persistence.jpa.mapper;

import com.fiap.domain.model.Alerta;
import com.fiap.infrastructure.persistence.jpa.entity.AlertaJPAEntity;

public final class AlertaJpaMapper {

    private AlertaJpaMapper() {}

    public static Alerta toDomain(AlertaJPAEntity entity) {
        Alerta alerta = new Alerta(
                entity.id,
                entity.tipo,
                entity.gravidade,
                entity.unidadeId,
                entity.medicamentoId,
                entity.estoqueId,
                entity.loteId,
                entity.mensagem,
                entity.criadoEm
        );

        alerta.restaurarStatus(entity.status, entity.resolvidoEm);

        return alerta;
    }

    public static AlertaJPAEntity toEntity(Alerta domain) {
        AlertaJPAEntity entity = new AlertaJPAEntity();
        entity.id = domain.getId();
        entity.tipo = domain.getTipo();
        entity.gravidade = domain.getGravidade();
        entity.unidadeId = domain.getUnidadeId();
        entity.medicamentoId = domain.getMedicamentoId();
        entity.estoqueId = domain.getEstoqueId();
        entity.loteId = domain.getLoteId();
        entity.mensagem = domain.getMensagem();
        entity.status = domain.getStatus();
        entity.criadoEm = domain.getCriadoEm();
        entity.resolvidoEm = domain.getResolvidoEm();
        return entity;
    }
}
