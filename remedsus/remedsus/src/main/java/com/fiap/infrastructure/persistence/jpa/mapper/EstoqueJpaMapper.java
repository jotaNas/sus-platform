package com.fiap.infrastructure.persistence.jpa.mapper;

import com.fiap.domain.model.Estoque;
import com.fiap.domain.model.Lote;
import com.fiap.infrastructure.persistence.jpa.entity.EstoqueJPAEntity;
import com.fiap.infrastructure.persistence.jpa.entity.LoteJPAEntity;

import java.util.ArrayList;

public class EstoqueJpaMapper {

    public static Estoque toDomain(EstoqueJPAEntity entity) {
        Estoque estoque = new Estoque(
                entity.id,
                entity.unidadeId,
                entity.medicamentoId,
                entity.estoqueMinimo
        );

        estoque.restaurarSaldo(entity.saldoAtual);

        entity.lotes.forEach(loteEntity -> {
            Lote lote = new Lote(
                    loteEntity.id,
                    loteEntity.medicamentoId,
                    loteEntity.numero,
                    loteEntity.validade,
                    loteEntity.quantidadeAtual
            );

            estoque.restaurarLote(lote);
        });

        estoque.limparEventos();

        return estoque;
    }

    public static EstoqueJPAEntity toEntity(Estoque domain) {
        EstoqueJPAEntity entity = new EstoqueJPAEntity();

        entity.id = domain.getId();
        entity.unidadeId = domain.getUnidadeId();
        entity.medicamentoId = domain.getMedicamentoId();
        entity.saldoAtual = domain.getSaldoAtual();
        entity.estoqueMinimo = domain.getEstoqueMinimo();
        entity.lotes = new ArrayList<>();

        domain.getLotes().forEach(lote -> {
            LoteJPAEntity loteEntity = new LoteJPAEntity();
            loteEntity.id = lote.getId();
            loteEntity.medicamentoId = domain.getMedicamentoId();
            loteEntity.numero = lote.getNumero();
            loteEntity.validade = lote.getValidade();
            loteEntity.quantidadeAtual = lote.getQuantidadeAtual();
            loteEntity.estoque = entity;

            entity.lotes.add(loteEntity);
        });

        return entity;
    }
}
