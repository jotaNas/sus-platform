package com.fiap.domain.dataaccess;

import com.fiap.domain.model.Medicamento;

import java.util.Optional;

public interface MedicamentoDataAccess {
    Optional<Medicamento> buscarPorId(String id);
    boolean existePorNomeEApresentacao(String nome, String apresentacao);
    void salvar(Medicamento medicamento);
}