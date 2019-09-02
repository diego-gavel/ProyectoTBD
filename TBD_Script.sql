create table Emergencia (
	id serial,
	nombre varchar(30),
	ubicacion varchar(40),
	tipo varchar(10),
	descripcion text,
	primary key(id)
);

create table Tareas (
	id serial,
	titulo varchar(30),
	estado varchar(20),
	primary key(id)
);


create table Implemento (
	id serial,
	nombre varchar(30),
	descripcion text,
	primary key(id)
);

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