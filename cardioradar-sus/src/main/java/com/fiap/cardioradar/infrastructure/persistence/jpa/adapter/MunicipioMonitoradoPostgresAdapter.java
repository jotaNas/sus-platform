package com.fiap.cardioradar.infrastructure.persistence.jpa.adapter;


import com.fiap.cardioradar.domain.MunicipioMonitorado;
import com.fiap.cardioradar.domain.dataaccess.MunicipioMonitoradoDataAccess;

import com.fiap.cardioradar.infrastructure.persistence.jpa.mapper.MunicipioMonitoradoJpaMapper;
import com.fiap.cardioradar.infrastructure.persistence.jpa.repository.MunicipioMonitoradoJpaRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.Optional;

import com.fiap.cardioradar.infrastructure.persistence.jpa.entity.MunicipioMonitoradoEntity;

import java.util.UUID;

@ApplicationScoped
public class MunicipioMonitoradoPostgresAdapter
        implements MunicipioMonitoradoDataAccess {

    private final MunicipioMonitoradoJpaRepository repository;

    public MunicipioMonitoradoPostgresAdapter(
            MunicipioMonitoradoJpaRepository repository
    ) {
        this.repository = repository;
    }

    @Override
    public void salvar(MunicipioMonitorado municipio) {
        Optional<MunicipioMonitoradoEntity> entityExistente =
                repository.findByIdOptional(
                        municipio.getId()
                );

        if (entityExistente.isPresent()) {
            MunicipioMonitoradoJpaMapper.updateEntity(
                    municipio,
                    entityExistente.get()
            );

            return;
        }

        MunicipioMonitoradoEntity entity =
                MunicipioMonitoradoJpaMapper.toEntity(
                        municipio
                );

        repository.persist(entity);
    }

    @Override
    public Optional<MunicipioMonitorado> buscarPorId(
            UUID id
    ) {
        return repository.findByIdOptional(id)
                .map(MunicipioMonitoradoJpaMapper::toDomain);
    }

    @Override
    public Optional<MunicipioMonitorado> buscarPorCodigoIbge(
            String codigoIbge
    ) {
        return repository.buscarPorCodigoIbge(codigoIbge)
                .map(MunicipioMonitoradoJpaMapper::toDomain);
    }

    @Override
    public boolean existePorCodigoIbge(
            String codigoIbge
    ) {
        return repository.existePorCodigoIbge(
                codigoIbge
        );
    }
}