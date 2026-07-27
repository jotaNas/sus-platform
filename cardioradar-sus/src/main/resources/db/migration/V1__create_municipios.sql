CREATE TABLE municipios (
                            id UUID NOT NULL,
                            codigo_ibge VARCHAR(7) NOT NULL,
                            nome VARCHAR(150) NOT NULL,
                            uf VARCHAR(2) NOT NULL,
                            ativo BOOLEAN NOT NULL DEFAULT TRUE,
                            criado_em TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                            atualizado_em TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,

                            CONSTRAINT pk_municipios
                                PRIMARY KEY (id),

                            CONSTRAINT uk_municipios_codigo_ibge
                                UNIQUE (codigo_ibge),

                            CONSTRAINT ck_municipios_codigo_ibge
                                CHECK (codigo_ibge ~ '^[0-9]{7}$'),

    CONSTRAINT ck_municipios_nome
        CHECK (CHAR_LENGTH(TRIM(nome)) >= 2),

    CONSTRAINT ck_municipios_uf
        CHECK (uf ~ '^[A-Z]{2}$')
);

CREATE INDEX idx_municipios_nome
    ON municipios (nome);

CREATE INDEX idx_municipios_uf
    ON municipios (uf);

CREATE INDEX idx_municipios_ativo
    ON municipios (ativo);

CREATE INDEX idx_municipios_uf_ativo
    ON municipios (uf, ativo);