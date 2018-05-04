CREATE TABLE teams (
	id integer auto_increment primary key,
	nombre varchar(128),
	create_at DATETIME,
	update_at DATETIME
)ENGINE=InnoDB;