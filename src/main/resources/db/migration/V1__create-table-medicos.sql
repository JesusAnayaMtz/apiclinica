create table medicos (
id bigint not null auto_increment,
nombre varchar(100) not null,
email varchar(100) not null unique,
documento varchar(6) not null unique,
especialidad varchar(50) not null,
calle varchar(50) not null,
distrito varchar(50) not null,
complemento varchar(70),
numero varchar(7),
ciudad varchar(50) not null,

primary key(id)
);