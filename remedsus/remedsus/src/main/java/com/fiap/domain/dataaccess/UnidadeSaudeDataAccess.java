package com.fiap.domain.dataaccess;


import com.fiap.domain.model.UnidadeSaude;

import java.util.Optional;

public interface UnidadeSaudeDataAccess {
    Optional<UnidadeSaude> buscarPorId(String id);
    void salvar(UnidadeSaude unidade);
}