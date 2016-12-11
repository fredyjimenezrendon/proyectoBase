# proyectoBase
Proyecto base para proyecto web Spring 4 + Spring Security 4 + Hibernate 4 + MySQL

***Base de datos***

CREATE TABLE `usuario` (
	`id_usuario` INT(11) NOT NULL AUTO_INCREMENT,
	`login` VARCHAR(50) NOT NULL,
	`password` VARCHAR(255) NOT NULL,
	`fecha_inicial` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
	`habilitado` TINYINT(1) NOT NULL DEFAULT '1',
	PRIMARY KEY (`id_usuario`)
);

CREATE TABLE `rol` (
	`id_rol` INT(11) NOT NULL AUTO_INCREMENT,
	`nombre` VARCHAR(50) NOT NULL,
	PRIMARY KEY (`id_rol`)
);

CREATE TABLE `usuario_rol` (
	`id_usuario_rol` INT(11) NOT NULL AUTO_INCREMENT,
	`id_usuario` INT(11) NOT NULL,
	`id_rol` INT(11) NOT NULL,
	PRIMARY KEY (`id_usuario_rol`),
	CONSTRAINT `usuario_rol_fk01` FOREIGN KEY (`id_usuario`) REFERENCES `usuario` (`id_usuario`),
	CONSTRAINT `usuario_rol_fk02` FOREIGN KEY (`id_rol`) REFERENCES `rol` (`id_rol`)
);

CREATE TABLE `persistent_logins` ( 
  `username` VARCHAR(100) NOT NULL, 
  `series` VARCHAR(64) PRIMARY KEY, 
  `token` VARCHAR(64) NOT NULL, 
  `last_used` timestamp NOTT NULL
);
