create table bets(
	id integer auto_increment primary key,
	fecha_apuesta datetime,
	id_schedule integer,
	id_usuario_apostador integer,
	constraint control_schedule foreign key (id_schedule) references schedules(id),
	constraint control_usuario foreign key (id_usuario_apostador) references users(id),
	create_at DATETIME,
	update_at DATETIME
)ENGINE=InnoDB;
