create table alertas (
                         id varchar(36) not null,
                         tipo varchar(40) not null,
                         gravidade varchar(20) not null,
                         unidade_id varchar(36) not null,
                         medicamento_id varchar(36) not null,
                         estoque_id varchar(36),
                         lote_id varchar(36),
                         mensagem varchar(500) not null,
                         status varchar(20) not null,
                         criado_em timestamp not null,
                         resolvido_em timestamp,
                         primary key (id)
);

create index idx_alertas_status
    on alertas (status);

create index idx_alertas_estoque
    on alertas (estoque_id);

create index idx_alertas_unidade_medicamento
    on alertas (unidade_id, medicamento_id);

create index idx_alertas_tipo_status
    on alertas (tipo, status);