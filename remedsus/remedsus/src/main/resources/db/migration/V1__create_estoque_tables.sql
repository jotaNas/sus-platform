create table medicamentos (
                              id varchar(255) not null,
                              nome varchar(255) not null,
                              principio_ativo varchar(255) not null,
                              apresentacao varchar(255) not null,
                              ativo boolean not null,
                              primary key (id)
);

create table unidades_saude (
                                id varchar(255) not null,
                                nome varchar(255) not null,
                                tipo varchar(50) not null,
                                municipio varchar(255) not null,
                                bairro varchar(255) not null,
                                ativa boolean not null,
                                primary key (id)
);

create table estoques (
                          id varchar(255) not null,
                          unidade_id varchar(255) not null,
                          medicamento_id varchar(255) not null,
                          saldo_atual integer not null,
                          estoque_minimo integer not null,
                          primary key (id),
                          constraint fk_estoques_unidades
                              foreign key (unidade_id)
                                  references unidades_saude(id),
                          constraint fk_estoques_medicamentos
                              foreign key (medicamento_id)
                                  references medicamentos(id)
);

create table lotes (
                       id varchar(255) not null,
                       medicamento_id varchar(255) not null,
                       numero varchar(255) not null,
                       validade date not null,
                       quantidade_atual integer not null,
                       estoque_id varchar(255) not null,
                       primary key (id),
                       constraint fk_lotes_estoques
                           foreign key (estoque_id)
                               references estoques(id),
                       constraint fk_lotes_medicamentos
                           foreign key (medicamento_id)
                               references medicamentos(id)
);

create unique index uk_medicamentos_nome_apresentacao
    on medicamentos (nome, apresentacao);

create index idx_estoques_unidade_medicamento
    on estoques (unidade_id, medicamento_id);

create index idx_lotes_estoque
    on lotes (estoque_id);