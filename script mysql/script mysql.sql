create schema hotel_alura;
use hotel_alura;

create table usuario(
id int auto_increment,
username varchar(50) unique not null,
`password` varchar(50) not null,
flag bit,
primary key (id));

create table persona (
id int auto_increment,
dni varchar(50) not null unique,
pasaporte varchar(50) unique,
nombre varchar(50) not null,
apellido varchar(50) not null,
nacionalidad varchar(50),
telefono varchar(50) not null,
fecha_nacimiento date,
id_usuario int,
flag bit,
primary key (id),
foreign key (id_usuario) references usuario(id) on delete cascade on update cascade);

create table tipo_habitacion(
id int auto_increment,
nombre varchar(50) not null,
precio decimal not null,
flag bit,
primary key (id));


create table habitacion (
id int auto_increment,
id_tipo_habitacion int,
nro_habitacion int not null,
estado bit,
flag bit,
primary key (id),
foreign key (id_tipo_habitacion) references tipo_habitacion(id) on delete cascade on update cascade);


create table reserva (
id int auto_increment,
id_usuario_registra int not null,
id_persona_huesped int not null,
fecha_check_in date not null,
fecha_check_out date not null,
flag bit,
primary key (id),
foreign key (id_usuario_registra) references persona(id_usuario) on delete cascade on update cascade,
foreign key (id_persona_huesped) references persona(id) on delete cascade on update cascade);

create table detalle_reserva(
id int auto_increment,
id_reserva int not null,
id_habitacion int not null,
precio decimal not null,
flag bit,
primary key(id),
foreign key (id_reserva) references reserva(id) on delete cascade on update cascade,
foreign key (id_habitacion) references habitacion(id) on delete cascade on update cascade);

insert into persona(dni,nombre,apellido,nacionalidad,telefono,fecha_nacimiento,flag) values ('1234567','pepito','perez','peruana','3205432765','2000-10-25',1);
insert into usuario(username,`password`,flag) values ('carolina12', 'caro123',1);
delete from usuario where username = 'pepito45';
insert into persona(dni,nombre,apellido,nacionalidad,telefono,fecha_nacimiento,username,flag) values ('2345678','carolina','ibarra','colombiana','3205432784','2000-08-10','carolina12',1);


