# ProyectoTBD

#Script base de datos Postresql

create table Usuario (
	id_usuario serial,
	rut varchar(10),
	nombre varchar(30),
	edad int,
	correo varchar(60),
	celular varchar(20),
	primary key(id_usuario)
);


create table Voluntario (
	id serial,
	rut varchar(10),
	nombre varchar(40),
	edad int,
	correo varchar(60),
	celular varchar(20),
	peso int, 
	estatura int,
	primary key(id)
);
