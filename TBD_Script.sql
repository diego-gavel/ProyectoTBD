create table Emergencia (
	id_emergencia serial,
	nombre varchar(30),
	ubicacion varchar(40),
	tipo varchar(10),
	descripcion text,
	primary key(id_emergencia)
);

create table Tarea (
	id_tarea serial,
	titulo varchar(30),
	estado varchar(20),
	primary key(id_tarea)
);


create table Rol (
	id_rol serial,
	nombre varchar(30),
	permisos varchar,
	primary key(id_rol)
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
	id_voluntario serial,
	rut varchar(10),
	nombre varchar(40),
	edad int,
	correo varchar(60),
	celular varchar(20),
	peso float, 
	estatura float,
	primary key(id_voluntario)
);

--Modificar para despues
create table Caracteristica (
	id_caracteristica serial,
	nombre varchar(30),
	primary key(id_caracteristica)
);
