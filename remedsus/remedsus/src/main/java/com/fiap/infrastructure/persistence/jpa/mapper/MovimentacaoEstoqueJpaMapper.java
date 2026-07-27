package com.fiap.infrastructure.persistence.jpa.mapper;

import com.fiap.domain.model.MovimentacaoEstoque;
import com.fiap.infrastructure.persistence.jpa.entity.MovimentacaoEstoqueJPAEntity;

public final class MovimentacaoEstoqueJpaMapper {

    private MovimentacaoEstoqueJpaMapper() {}

    public static MovimentacaoEstoque toDomain(MovimentacaoEstoqueJPAEntity entity) {
        return new MovimentacaoEstoque(
                entity.id,
                entity.estoqueId,
                entity.unidadeId,
                entity.medicamentoId,
                entity.tipo,
                entity.quantidade,
                entity.realizadaEm,
                entity.loteId,
                entity.origemEvento
        );
    }

    public static MovimentacaoEstoqueJPAEntity toEntity(MovimentacaoEstoque domain) {
        MovimentacaoEstoqueJPAEntity entity = new MovimentacaoEstoqueJPAEntity();
        entity.id = domain.getId();
        entity.estoqueId = domain.getEstoqueId();
        entity.unidadeId = domain.getUnidadeId();
        entity.medicamentoId = domain.getMedicamentoId();
        entity.tipo = domain.getTipo();
        entity.quantidade = domain.getQuantidade();
        entity.realizadaEm = domain.getRealizadaEm();
        entity.loteId = domain.getLoteId();
        entity.origemEvento = domain.getOrigemEvento();
        return entity;
    }
}
