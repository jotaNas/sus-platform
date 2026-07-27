package com.fiap.domain.dataaccess;

import com.fiap.domain.model.Estoque;

import java.util.Optional;

public interface EstoqueDataAccess {

    Optional<Estoque> buscarPorUnidadeEMedicamento(
            String unidadeId,
            String medicamentoId
    );

    void salvar(Estoque estoque);
}
