package com.fiap.cardioradar.infrastructure.persistence.jpa.adapter;

import com.fiap.cardioradar.domain.IndicadorCardiovascular;
import com.fiap.cardioradar.domain.dataaccess.IndicadorCardiovascularDataAccess;
import com.fiap.cardioradar.infrastructure.persistence.jpa.mapper.IndicadorCardiovascularJpaMapper;
import com.fiap.cardioradar.infrastructure.persistence.jpa.repository.IndicadorCardiovascularJpaRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.time.YearMonth;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@ApplicationScoped
public class IndicadorCardiovascularPostgresAdapter
        implements IndicadorCardiovascularDataAccess {

    private final IndicadorCardiovascularJpaRepository repository;

    public IndicadorCardiovascularPostgresAdapter(
            IndicadorCardiovascularJpaRepository repository
    ) {
        this.repository = repository;
    }

    @Override
    public Optional<IndicadorCardiovascular> buscarPorId(
            UUID id
    ) {
        if (id == null) {
            throw new IllegalArgumentException(
                    "Id do indicador é obrigatório."
            );
        }

        return repository.findByIdOptional(id)
                .map(IndicadorCardiovascularJpaMapper::toDomain);
    }

    @Override
    public Optional<IndicadorCardiovascular>
    buscarPorMunicipioECompetencia(
            UUID municipioId,
            YearMonth competencia
    ) {
        if (municipioId == null) {
            throw new IllegalArgumentException(
                    "Id do município é obrigatório."
            );
        }

        if (competencia == null) {
            throw new IllegalArgumentException(
                    "Competência é obrigatória."
            );
        }

        return repository.buscarPorMunicipioECompetencia(
                        municipioId,
                        competencia.toString()
                )
                .map(IndicadorCardiovascularJpaMapper::toDomain);
    }

    @Override
    public List<IndicadorCardiovascular> listarPorMunicipio(
            UUID municipioId
    ) {
        if (municipioId == null) {
            throw new IllegalArgumentException(
                    "Id do município é obrigatório."
            );
        }

        return repository.listarPorMunicipio(municipioId)
                .stream()
                .map(IndicadorCardiovascularJpaMapper::toDomain)
                .toList();
    }

    @Override
    public boolean existePorMunicipioECompetencia(
            UUID municipioId,
            YearMonth competencia
    ) {
        if (municipioId == null) {
            throw new IllegalArgumentException(
                    "Id do município é obrigatório."
            );
        }

        if (competencia == null) {
            throw new IllegalArgumentException(
                    "Competência é obrigatória."
            );
        }

        return repository.existePorMunicipioECompetencia(
                municipioId,
                competencia.toString()
        );
    }

    @Override
    public void salvar(
            IndicadorCardiovascular indicador
    ) {
        if (indicador == null) {
            throw new IllegalArgumentException(
                    "Indicador cardiovascular é obrigatório."
            );
        }

        repository.findByIdOptional(indicador.getId())
                .ifPresentOrElse(
                        entity ->
                                IndicadorCardiovascularJpaMapper
                                        .updateEntity(
                                                indicador,
                                                entity
                                        ),
                        () -> repository.persist(
                                IndicadorCardiovascularJpaMapper
                                        .toEntity(indicador)
                        )
                );
    }
}