--user: "tbduser" / pass: "tbdpass"

CREATE ROLE "tbduser" WITH PASSWORD "tbdpass"
	LOGIN
	SUPERUSER
	INHERIT
	CREATEDB
	CREATEROLE
	REPLICATION;

CREATE TABLE Emergencia (
	id_emergencia SERIAL,
	nombre VARCHAR(30),
	ubicacion VARCHAR(40),
	tipo VARCHAR(10),
	descripcion TEXT,
	PRIMARY KEY(id_emergencia)
);

CREATE TABLE Tarea (
	id_tarea SERIAL,
	titulo VARCHAR(30),
	estado VARCHAR(20),
	PRIMARY KEY(id_tarea)
);

--revisar permisos, por ahora es TEXT
CREATE TABLE Rol (
	id_rol SERIAL,
	nombre VARCHAR(30),
	permisos TEXT,
	PRIMARY KEY(id_rol)
);

CREATE TABLE Usuario (
	id_usuario SERIAL,
	rut VARCHAR(10),
	nombre VARCHAR(30),
	edad INT,
	correo VARCHAR(60),
	celular VARCHAR(20),
	PRIMARY KEY(id_usuario)
);


CREATE TABLE Voluntario (
	id_voluntario SERIAL,
	rut VARCHAR(10),
	nombre VARCHAR(40),
	edad INT,
	correo VARCHAR(60),
	celular VARCHAR(20),
	peso FLOAT, 
	estatura FLOAT,
	PRIMARY KEY(id_voluntario)
);

--Modificar para despues
CREATE TABLE Caracteristica (
	id_caracteristica SERIAL,
	nombre VARCHAR(30),
	PRIMARY KEY(id_caracteristica)
);
