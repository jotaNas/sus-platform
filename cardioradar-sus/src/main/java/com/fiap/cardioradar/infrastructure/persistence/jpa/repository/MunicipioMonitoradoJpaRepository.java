package com.fiap.cardioradar.infrastructure.persistence.jpa.repository;

import com.fiap.cardioradar.infrastructure.persistence.jpa.entity.MunicipioMonitoradoEntity;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.Optional;

import java.util.UUID;

@ApplicationScoped
public class MunicipioMonitoradoJpaRepository
        implements PanacheRepositoryBase<
        MunicipioMonitoradoEntity,
        UUID
        > {

    public Optional<MunicipioMonitoradoEntity>
    buscarPorCodigoIbge(String codigoIbge) {
        return find(
                "codigoIbge",
                codigoIbge
        ).firstResultOptional();
    }

    public boolean existePorCodigoIbge(
            String codigoIbge
    ) {
        return count(
                "codigoIbge",
                codigoIbge
        ) > 0;
    }
}