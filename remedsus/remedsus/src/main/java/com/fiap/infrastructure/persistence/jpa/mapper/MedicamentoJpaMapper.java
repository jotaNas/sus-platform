package com.fiap.infrastructure.persistence.jpa.mapper;


import com.fiap.domain.model.Medicamento;
import com.fiap.infrastructure.persistence.jpa.entity.MedicamentoJPAEntity;

public class MedicamentoJpaMapper {

    public static Medicamento toDomain(MedicamentoJPAEntity entity) {
        Medicamento medicamento = new Medicamento(
                entity.id,
                entity.nome,
                entity.principioAtivo,
                entity.apresentacao
        );

        if (!entity.ativo) {
            medicamento.inativar();
        }


        return medicamento;
    }

    public static MedicamentoJPAEntity toEntity(Medicamento domain) {
        MedicamentoJPAEntity entity = new MedicamentoJPAEntity();
        entity.id = domain.getId();
        entity.nome = domain.getNome();
        entity.principioAtivo = domain.getPrincipioAtivo();
        entity.apresentacao = domain.getApresentacao();
        entity.ativo = domain.isAtivo();
        return entity;
    }
}