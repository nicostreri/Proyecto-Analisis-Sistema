create table matches(
	id integer auto_increment primary key,
	fecha datetime,
	schedule_pertenece integer,

	constraint ref_schedule foreign key (schedule_pertenece) references schedules (id),
	create_at DATETIME,
	update_at DATETIME
)ENGINE=InnoDB;
