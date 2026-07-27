package com.fiap.infrastructure.persistence.jpa.adapter;

import com.fiap.domain.dataaccess.AlertaDataAccess;
import com.fiap.domain.model.Alerta;

import com.fiap.domain.vo.StatusAlerta;
import com.fiap.domain.vo.TipoAlerta;
import com.fiap.infrastructure.persistence.jpa.mapper.AlertaJpaMapper;
import com.fiap.infrastructure.persistence.jpa.repository.AlertaJpaRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class AlertaPostgresAdapter implements AlertaDataAccess {

    private final AlertaJpaRepository repository;

    public AlertaPostgresAdapter(AlertaJpaRepository repository) {
        this.repository = repository;
    }

    @Override
    public void salvar(Alerta alerta) {
        repository.getEntityManager().merge(AlertaJpaMapper.toEntity(alerta));
    }

    @Override
    public Optional<Alerta> buscarAbertoPorTipoUnidadeMedicamento(
            TipoAlerta tipo,
            String unidadeId,
            String medicamentoId
    ) {
        return repository.findAbertoPorTipoUnidadeMedicamento(tipo, unidadeId, medicamentoId)
                .map(AlertaJpaMapper::toDomain);
    }

    @Override
    public List<Alerta> listarPorStatus(StatusAlerta status) {
        return repository.findByStatus(status)
                .stream()
                .map(AlertaJpaMapper::toDomain)
                .toList();
    }

    @Override
    public List<Alerta> listarPorEstoqueId(String estoqueId) {
        return repository.findByEstoqueId(estoqueId)
                .stream()
                .map(AlertaJpaMapper::toDomain)
                .toList();
    }
}
