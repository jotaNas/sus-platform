create table movimentacoes_estoque (
                                       id varchar(36) not null,
                                       estoque_id varchar(36),
                                       unidade_id varchar(36) not null,
                                       medicamento_id varchar(36) not null,
                                       tipo varchar(30) not null,
                                       quantidade integer not null,
                                       realizada_em timestamp not null,
                                       lote_id varchar(36),
                                       origem_evento varchar(100),
                                       primary key (id)
);

create index idx_movimentacoes_estoque_id
    on movimentacoes_estoque (estoque_id);

create index idx_movimentacoes_unidade_medicamento
    on movimentacoes_estoque (unidade_id, medicamento_id);

create index idx_movimentacoes_realizada_em
    on movimentacoes_estoque (realizada_em);