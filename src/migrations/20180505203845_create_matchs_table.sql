create table matches(
	id INTEGER AUTO_INCREMENT PRIMARY KEY,
	fecha DATETIME,
	id_schedule INTEGER,
	id_team_visitante INTEGER,
	id_team_local INTEGER,
	id_result INTEGER,
	CONSTRAINT control_schedule_match FOREIGN KEY (id_schedule) REFERENCES schedules (id),
	CONSTRAINT control_team_visit_match FOREIGN KEY (id_team_visitante) REFERENCES teams (id),
	CONSTRAINT control_team_local_match FOREIGN KEY (id_team_local) REFERENCES teams(id),
	CONSTRAINT control_teams_result_match FOREIGN KEY (id_result) REFERENCES results(id),
	create_at DATETIME,
	update_at DATETIME
)ENGINE=InnoDB;