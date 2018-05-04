create table schedules(
	id integer auto_increment primary key,
	nombre_fechasas varchar(30),
	fixture_pertenece int(11),
	constraint ref_fixture foreign key (fixture_pertenece) references fixtures (id),
	create_at DATETIME,
	update_at DATETIME
)ENGINE=InnoDB;