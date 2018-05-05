create table matches(
	id integer auto_increment primary key,
	fecha datetime,
	schedule_pertenece integer,
	team_visitante integer,
	team_local integer,
	resultado integer,
	constraint ref_schedule foreign key (schedule_pertenece) references schedules (id),
	constraint ref_teams_visit foreign key (team_visitante) references teams (id),
	constraint ref_teams_local foreign key (team_local ) references teams(id),
	constraint ref_teams_result foreign key (resultado) references results(id),
	create_at DATETIME,
	update_at DATETIME
)ENGINE=InnoDB;