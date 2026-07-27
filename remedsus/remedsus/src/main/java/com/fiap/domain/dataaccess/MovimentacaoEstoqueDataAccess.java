package com.fiap.domain.dataaccess;

import com.fiap.domain.model.MovimentacaoEstoque;

import java.util.List;

public interface MovimentacaoEstoqueDataAccess {
    void salvar(MovimentacaoEstoque movimentacao);
    List<MovimentacaoEstoque> listarPorEstoqueId(String estoqueId);
}
