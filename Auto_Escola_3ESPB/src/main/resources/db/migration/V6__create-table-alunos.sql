create table alunos(
    id bigint not null auto_increment,
    nome varchar(100) not null,
    email varchar(100) not null unique,
    cpf varchar(11) not null unique,
    logradouro varchar(255) not null,
    numero varchar(20),
    complemento varchar(100),
    bairro varchar(100) not null,
    cidade varchar(100) not null,
    uf varchar(2) not null,
    cep varchar(8) not null,
    ativo tinyint default(1),

    primary key(id)
);