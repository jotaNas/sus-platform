package com.fiap.cardioradar.infrastructure.persistence.jpa.repository;


import com.fiap.cardioradar.domain.vo.NivelRisco;
import com.fiap.cardioradar.infrastructure.persistence.jpa.entity.RiscoMunicipalEntity;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@ApplicationScoped
public class RiscoMunicipalJpaRepository
        implements PanacheRepositoryBase<
        RiscoMunicipalEntity,
        UUID
        > {

    public Optional<RiscoMunicipalEntity>
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

    public Optional<RiscoMunicipalEntity>
    buscarUltimoPorMunicipio(UUID municipioId) {
        return find(
                "municipioId = ?1 order by competencia desc",
                municipioId
        ).firstResultOptional();
    }

    public List<RiscoMunicipalEntity> listarPorMunicipio(
            UUID municipioId
    ) {
        return list(
                "municipioId = ?1 order by competencia desc",
                municipioId
        );
    }

    public List<RiscoMunicipalEntity> listarPorNivel(
            NivelRisco nivel
    ) {
        return list(
                "nivel = ?1 order by indicePressaoCardiovascular desc",
                nivel
        );
    }
    public boolean existePorIndicadorId(String indicadorId) {
        return count(
                "indicadorId",
                indicadorId
        ) > 0;
    }

    public List<RiscoMunicipalEntity> listarPorCompetencia(
            String competencia
    ) {
        return find(
                "competencia = ?1 order by indicePressaoCardiovascular desc",
                competencia
        ).list();
    }
}
