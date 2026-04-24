alter table instrucoes
    add column status varchar(20) not null default 'AGENDADA',
    add column motivo_cancelamento varchar(30) null,
    add column data_hora_cancelamento datetime null;
