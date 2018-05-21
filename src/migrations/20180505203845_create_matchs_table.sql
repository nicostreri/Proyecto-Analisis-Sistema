create table matches(
	id INTEGER AUTO_INCREMENT PRIMARY KEY,
	fecha DATETIME,
	schedule_id INTEGER,
	visitor_team_id INTEGER,
	local_team_id INTEGER,
	result_id INTEGER,
	CONSTRAINT control_schedule_match FOREIGN KEY (schedule_id) REFERENCES schedules (id),
	CONSTRAINT control_team_visit_match FOREIGN KEY (visitor_team_id) REFERENCES teams (id),
	CONSTRAINT control_team_local_match FOREIGN KEY (local_team_id) REFERENCES teams(id),
	CONSTRAINT control_teams_result_match FOREIGN KEY (result_id) REFERENCES results(id),
	create_at DATETIME,
	update_at DATETIME
)ENGINE=InnoDB;
