create table schedules(
	id integer auto_increment primary key,
	nombre_fechas varchar(30),
	id_fixture integer,
	constraint ref_fixture foreign key (id_fixture) references fixtures (id),
	create_at DATETIME,
	update_at DATETIME
)ENGINE=InnoDB;