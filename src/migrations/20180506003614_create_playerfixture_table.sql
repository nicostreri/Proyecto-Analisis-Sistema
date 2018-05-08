create table players_fixtures (	
	--id INTEGER AUTO_INCREMENT PRIMARY KEY,
	player_username varchar(128),
	fixture_id INTEGER,
	primary key (player_username,fixture_id),
	CONSTRAINT control_player_suscripcion FOREIGN KEY (player_username) REFERENCES players(username),
	CONSTRAINT control_fixture_suscripcion FOREIGN KEY (fixture_id) REFERENCES fixtures(id),
	created_at DATETIME,
  	updated_at DATETIME
)ENGINE=InnoDB;
