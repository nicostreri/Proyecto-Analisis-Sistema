create table players_fixtures (	
	--id INTEGER AUTO_INCREMENT PRIMARY KEY,
	username_player varchar(128),
	fixture_id INTEGER,
	primary key (username_player,fixture_id),
	CONSTRAINT control_player_suscripcion FOREIGN KEY (username_player) REFERENCES players(username),
	CONSTRAINT control_fixture_suscripcion FOREIGN KEY (fixture_id) REFERENCES fixtures(id),
	created_at DATETIME,
  	updated_at DATETIME
)ENGINE=InnoDB;
