create database frete;

\c frete
create table veiculo
(
    id         serial
        constraint veiculo_pk
            primary key,
    descricao  varchar(100)         not null,
    placa      varchar(100)         not null,
    capacidade integer default 0    not null,
    ativo      boolean default true not null
);
create table cliente
(
    id       serial
        constraint cliente_pk
            primary key,
    nome     varchar(200)         not null,
    email    varchar(100)         not null,
    telefone varchar(100)         not null,
    ativo    boolean default true not null
);
create table frete
(
    id         serial
        constraint frete_pk
            primary key,
    id_cliente bigint                      not null
        constraint frete_cliente_id_fk
            references cliente,
    id_veiculo bigint
        constraint frete_veiculo_id_fk
            references veiculo,
    flg_tipo   char    default 'N'::bpchar not null,
    ativo      boolean default true        not null
);

create table item_carga
(
    id         serial
        constraint carga_pk
            primary key,
    descricao    varchar(200)                                      not null,
    valor_aprox  integer,
    flg_seguro   boolean default false                             not null,
    altura       double precision,
    largura      double precision,
    profundidade double precision,
    peso         integer,
    id_frete     bigint
        constraint item_carga_frete_id_fk
            references frete,
    ativo        boolean default true                              not null
);




INSERT INTO public.cliente (id, nome, email, telefone, ativo) VALUES (3, 'EXCLUJDE Fulano de Tal', 'fulano@gmail.com', '61 98499841', true);
INSERT INTO public.cliente (id, nome, email, telefone, ativo) VALUES (2, 'Sr. Fulano de Tal 2 ', 'fulano@gmail.com', '61 98499841', false);


INSERT INTO public.veiculo (id, descricao, placa, capacidade, ativo) VALUES (1, 'VW Titan', 'OGG5G00', 5000, true);
INSERT INTO public.veiculo (id, descricao, placa, capacidade, ativo) VALUES (2, 'F 4000', 'KJH7J88', 2500, true);

INSERT INTO public.frete (id, id_cliente, id_veiculo, flg_tipo, ativo) VALUES (11, 3, 2, 'P', true);

INSERT INTO public.item_carga (id, descricao, valor_aprox, flg_seguro, altura, largura, profundidade, peso, id_frete, ativo) VALUES (4, 'Fardos Grãos', 5000, false, null, null, null, 4800, 11, true);
comment on column frete.flg_tipo is 'N = Nenhum, P = Peso, C = cubagem';

