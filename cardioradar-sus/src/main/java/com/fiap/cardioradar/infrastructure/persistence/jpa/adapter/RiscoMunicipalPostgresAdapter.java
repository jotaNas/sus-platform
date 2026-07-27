package com.fiap.cardioradar.infrastructure.persistence.jpa.adapter;


import com.fiap.cardioradar.domain.RiscoMunicipal;
import com.fiap.cardioradar.domain.dataaccess.RiscoMunicipalDataAccess;
import com.fiap.cardioradar.infrastructure.persistence.jpa.mapper.RiscoMunicipalJpaMapper;
import com.fiap.cardioradar.infrastructure.persistence.jpa.repository.RiscoMunicipalJpaRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.time.YearMonth;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


@ApplicationScoped
public class RiscoMunicipalPostgresAdapter
        implements RiscoMunicipalDataAccess {

    private final RiscoMunicipalJpaRepository repository;

    public RiscoMunicipalPostgresAdapter(
            RiscoMunicipalJpaRepository repository
    ) {
        this.repository = repository;
    }

    @Override
    public Optional<RiscoMunicipal> buscarPorMunicipioECompetencia(
            UUID municipioId,
            YearMonth competencia
    ) {
        return repository
                .buscarPorMunicipioECompetencia(
                        municipioId,
                        competencia.toString()
                )
                .map(RiscoMunicipalJpaMapper::toDomain);
    }

    @Override
    public Optional<RiscoMunicipal> buscarUltimoPorMunicipio(
            UUID municipioId
    ) {
        return repository
                .buscarUltimoPorMunicipio(municipioId)
                .map(RiscoMunicipalJpaMapper::toDomain);
    }

    @Override
    public List<RiscoMunicipal> listarPorMunicipio(
            UUID municipioId
    ) {
        return repository
                .listarPorMunicipio(municipioId)
                .stream()
                .map(RiscoMunicipalJpaMapper::toDomain)
                .toList();
    }


    @Override
    public List<RiscoMunicipal> listarPorCompetencia(
            YearMonth competencia
    ) {
        return repository
                .listarPorCompetencia(
                      (competencia.toString())
                )
                .stream()
                .map(RiscoMunicipalJpaMapper::toDomain)
                .toList();
    }

    @Override
    public void salvar(
            RiscoMunicipal risco
    ) {
        if (risco == null) {
            throw new IllegalArgumentException(
                    "Risco municipal é obrigatório."
            );
        }

        repository
                .findByIdOptional(risco.getId())
                .ifPresentOrElse(
                        entity ->
                                RiscoMunicipalJpaMapper.updateEntity(
                                        risco,
                                        entity
                                ),
                        () ->
                                repository.persist(
                                        RiscoMunicipalJpaMapper.toEntity(
                                                risco
                                        )
                                )
                );
    }
}