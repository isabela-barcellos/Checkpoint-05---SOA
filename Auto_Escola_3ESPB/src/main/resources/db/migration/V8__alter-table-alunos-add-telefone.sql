alter table alunos
    add column telefone varchar(20) not null default '00000000000' after email;
