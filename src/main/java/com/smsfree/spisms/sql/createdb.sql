create table if not exists users(

    id serial primary key,
    nome varchar(70),
    login varchar(70),
    senha varchar(20),
    token varchar(100),
    ativo smallint,
    type smallint -- 0 adm full e 1 usuario commum
);
insert into users(nome,login,senha,type,ativo,token) values('Administrador', 'admin', '123',0,1,'adonai');

create table if not exists torpedos(

    id serial primary key,
    mensagem varchar(500),
    idusuario int,
    status smallint,
    dataagendamento timestamp,
    dataenvio timestamp
);

alter table torpedos add constraint fk_users foreign key (idusuario) references users(id) on delete cascade;

create table if not exists destinatarios(
    id serial primary key,
    idmensagem int,
    fone varchar(11)
);
alter table destinatarios add constraint fk_users foreign key (idmensagem) references torpedos(id);