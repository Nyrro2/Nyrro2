
 create table tema(
     id bigint not null auto_increment,
     titulo varchar(250) not null,
     publicacion varchar(400) not null,
     fecha_de_creacion datetime not null,
     status smallint,
     username varchar(100) not null,
     curso varchar(100) not null,

     primary key(id)
     )














