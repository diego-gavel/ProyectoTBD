--user: "tbduser" / pass: "tbdpass"

CREATE ROLE tbduser WITH PASSWORD 'tbdpass'
	LOGIN
	SUPERUSER
	INHERIT
	CREATEDB
	CREATEROLE
	REPLICATION;

CREATE EXTENSION postgis;





CREATE TABLE Emergencia (
	id_emergencia SERIAL,
	nombre VARCHAR(30),
	tipo VARCHAR(10),
	descripcion TEXT,
	latitude float(20),
	longitude float(20),
	PRIMARY KEY(id_emergencia)
);

-- Para que se agrege la columna locacion de la Emergencia
ALTER TABLE Emergencia ADD COLUMN location geometry(point);

CREATE TABLE Voluntario (
	id_voluntario SERIAL,
	nombre VARCHAR(40),
	apellido VARCHAR(40),
	correo VARCHAR(60),
	sexo VARCHAR(40),
	latitude float(20),
	longitude float(20),
	PRIMARY KEY(id_voluntario)
);

-- Para que se agrege la columna locacion del voluntario
ALTER TABLE Voluntario ADD COLUMN location geometry(point);

CREATE TABLE Tarea (
	id_tarea SERIAL,
	titulo VARCHAR(30),
	estado VARCHAR(20),
	id_voluntario int,
	id_emergencia int,
	PRIMARY KEY(id_tarea),
	FOREIGN KEY(id_voluntario)
	    REFERENCES voluntario(id_voluntario),
    FOREIGN KEY(id_emergencia)
        REFERENCES emergencia(id_emergencia)
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
	correo VARCHAR(60),
	celular VARCHAR(20),
	PRIMARY KEY(id_usuario)
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

insert into dimension(nombre) values ('Fuerza');
insert into dimension(nombre) values ('Destreza');
insert into dimension(nombre) values ('Liderazgo');
insert into dimension(nombre) values ('Motivacion');
insert into dimension(nombre) values ('Conocimiento');