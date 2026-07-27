CREATE TABLE riscos_municipais (
                                   id UUID NOT NULL,
                                   municipio_id UUID NOT NULL,
                                   indicador_id UUID NOT NULL,
                                   competencia VARCHAR(7) NOT NULL,

                                   indice_pressao_cardiovascular NUMERIC(10,2) NOT NULL,

                                   nivel VARCHAR(20) NOT NULL,
                                   tendencia VARCHAR(20) NOT NULL,

                                   calculado_em TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,

                                   CONSTRAINT pk_riscos_municipais
                                       PRIMARY KEY (id),

                                   CONSTRAINT fk_riscos_municipais_municipio
                                       FOREIGN KEY (municipio_id)
                                           REFERENCES municipios(id),

                                   CONSTRAINT fk_riscos_municipais_indicador
                                       FOREIGN KEY (indicador_id)
                                           REFERENCES indicadores_cardiovasculares(id),

                                   CONSTRAINT uk_risco_municipio_competencia
                                       UNIQUE (
                                               municipio_id,
                                               competencia
                                           ),

                                   CONSTRAINT uk_risco_indicador
                                       UNIQUE (
                                               indicador_id
                                           ),

                                   CONSTRAINT ck_risco_competencia
                                       CHECK (
                                           competencia ~ '^[0-9]{4}-(0[1-9]|1[0-2])$'
),

    CONSTRAINT ck_risco_indice
        CHECK (
            indice_pressao_cardiovascular >= 0
        )
);

CREATE INDEX idx_riscos_municipais_municipio
    ON riscos_municipais (municipio_id);

CREATE INDEX idx_riscos_municipais_competencia
    ON riscos_municipais (competencia);

CREATE INDEX idx_riscos_municipais_nivel
    ON riscos_municipais (nivel);

CREATE INDEX idx_riscos_municipais_tendencia
    ON riscos_municipais (tendencia);

CREATE INDEX idx_riscos_municipais_calculado_em
    ON riscos_municipais (calculado_em);

CREATE INDEX idx_riscos_municipais_municipio_competencia
    ON riscos_municipais (
                          municipio_id,
                          competencia DESC
        );