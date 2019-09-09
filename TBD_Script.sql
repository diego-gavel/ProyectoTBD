--user: "tbduser" / pass: "tbdpass"

CREATE ROLE tbduser WITH PASSWORD 'tbdpass'
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
	id_voluntario int,
	PRIMARY KEY(id_tarea),
	FOREIGN KEY(id_voluntario)
	REFERENCES voluntario(id_voluntario)
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
	nombre VARCHAR(40),
	apellido VARCHAR(40),
	edad INT,
	correo VARCHAR(60),
	sexo VARCHAR(40),
	PRIMARY KEY(id_voluntario)
);

--Modificar para despues
CREATE TABLE dimension (
	id_dimension SERIAL,
	nombre VARCHAR(30),
	PRIMARY KEY(id_dimension)
);

CREATE TABLE dimension_voluntario (
    id SERIAL,
    id_voluntario int,
    id_dimension int,
    valor int,
    PRIMARY KEY(id),
    FOREIGN KEY(id_voluntario)
        REFERENCES voluntario(id_voluntario),
    FOREIGN KEY(id_dimension)
        REFERENCES dimension(id_dimension)
);

CREATE TABLE emergencia_tarea (
    id SERIAL,
    id_emergencia int,
    id_tarea int,
    PRIMARY KEY(id),
    FOREIGN KEY(id_emergencia)
        REFERENCES Emergencia(id_emergencia),
    FOREIGN KEY(id_tarea)
        REFERENCES Tarea(id_tarea)
);