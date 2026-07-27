CREATE TABLE indicadores_cardiovasculares (
                                              id UUID NOT NULL,
                                              municipio_id UUID NOT NULL,
                                              competencia VARCHAR(7) NOT NULL,

                                              populacao_estimada INTEGER NOT NULL,
                                              populacao_idosa INTEGER NOT NULL,

                                              atendimentos_hipertensao INTEGER NOT NULL DEFAULT 0,
                                              atendimentos_diabetes INTEGER NOT NULL DEFAULT 0,
                                              internacoes_cardiovasculares INTEGER NOT NULL DEFAULT 0,
                                              obitos_cardiovasculares INTEGER NOT NULL DEFAULT 0,
                                              procedimentos_cardiovasculares INTEGER NOT NULL DEFAULT 0,

                                              fonte VARCHAR(30) NOT NULL,
                                              registrado_em TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,

                                              CONSTRAINT pk_indicadores_cardiovasculares
                                                  PRIMARY KEY (id),

                                              CONSTRAINT fk_indicadores_cardiovasculares_municipio
                                                  FOREIGN KEY (municipio_id)
                                                      REFERENCES municipios (id),

                                              CONSTRAINT uk_indicador_municipio_competencia
                                                  UNIQUE (
                                                          municipio_id,
                                                          competencia
                                                      ),

                                              CONSTRAINT ck_indicador_competencia
                                                  CHECK (
                                                      competencia ~ '^[0-9]{4}-(0[1-9]|1[0-2])$'
),

    CONSTRAINT ck_indicador_populacao_estimada
        CHECK (
            populacao_estimada > 0
        ),

    CONSTRAINT ck_indicador_populacao_idosa
        CHECK (
            populacao_idosa >= 0
            AND populacao_idosa <= populacao_estimada
        ),

    CONSTRAINT ck_indicador_atendimentos_hipertensao
        CHECK (
            atendimentos_hipertensao >= 0
        ),

    CONSTRAINT ck_indicador_atendimentos_diabetes
        CHECK (
            atendimentos_diabetes >= 0
        ),

    CONSTRAINT ck_indicador_internacoes_cardiovasculares
        CHECK (
            internacoes_cardiovasculares >= 0
        ),

    CONSTRAINT ck_indicador_obitos_cardiovasculares
        CHECK (
            obitos_cardiovasculares >= 0
        ),

    CONSTRAINT ck_indicador_procedimentos_cardiovasculares
        CHECK (
            procedimentos_cardiovasculares >= 0
        )
);

CREATE INDEX idx_indicadores_municipio
    ON indicadores_cardiovasculares (municipio_id);

CREATE INDEX idx_indicadores_competencia
    ON indicadores_cardiovasculares (competencia);

CREATE INDEX idx_indicadores_fonte
    ON indicadores_cardiovasculares (fonte);

CREATE INDEX idx_indicadores_registrado_em
    ON indicadores_cardiovasculares (registrado_em);

CREATE INDEX idx_indicadores_municipio_competencia
    ON indicadores_cardiovasculares (
                                     municipio_id,
                                     competencia DESC
        );