package com.fiap.infrastructure.persistence.memory;

import com.fiap.domain.model.Estoque;
import com.fiap.domain.dataaccess.EstoqueDataAccess;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

//@ApplicationScoped
public class InMemoryEstoqueDataAccess implements EstoqueDataAccess {

    private final Map<String, Estoque> database = new ConcurrentHashMap<>();

    @Override
    public Optional<Estoque> buscarPorUnidadeEMedicamento(
            String unidadeId,
            String medicamentoId
    ) {
        return Optional.ofNullable(database.get(key(unidadeId, medicamentoId)));
    }

    @Override
    public void salvar(Estoque estoque) {
        database.put(
                key(estoque.getUnidadeId(), estoque.getMedicamentoId()),
                estoque
        );
    }

    private String key(String unidadeId, String medicamentoId) {
        return unidadeId + "#" + medicamentoId;
    }
}
