package com.fiap.cardioradar.infrastructure.persistence.jpa.repository;

import com.fiap.cardioradar.infrastructure.persistence.jpa.entity.IndicadorCardiovascularEntity;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@ApplicationScoped
public class IndicadorCardiovascularJpaRepository
        implements PanacheRepositoryBase<
        IndicadorCardiovascularEntity,
        UUID
        > {

    public Optional<IndicadorCardiovascularEntity>
    buscarPorMunicipioECompetencia(
            UUID municipioId,
            String competencia
    ) {
        return find(
                "municipioId = ?1 and competencia = ?2",
                municipioId,
                competencia
        ).firstResultOptional();
    }

    public List<IndicadorCardiovascularEntity> listarPorMunicipio(
            UUID municipioId
    ) {
        return find(
                "municipioId = ?1 order by competencia desc",
                municipioId
        ).list();
    }

    public Optional<IndicadorCardiovascularEntity>
    buscarUltimoPorMunicipio(
            UUID municipioId
    ) {
        return find(
                "municipioId = ?1 order by competencia desc",
                municipioId
        ).firstResultOptional();
    }

    public boolean existePorMunicipioECompetencia(
            UUID municipioId,
            String competencia
    ) {
        return count(
                "municipioId = ?1 and competencia = ?2",
                municipioId,
                competencia
        ) > 0;
    }
}