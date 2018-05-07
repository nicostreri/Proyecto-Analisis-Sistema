create table playerfixtures (	
	--id INTEGER AUTO_INCREMENT PRIMARY KEY,
	username_player varchar(128),
	id_fixture INTEGER,
	primary key (username_player,id_fixture),
	CONSTRAINT control_player_suscripcion FOREIGN KEY (username_player) REFERENCES players(username),
	CONSTRAINT control_fixture_suscripcion FOREIGN KEY (id_fixture) REFERENCES fixtures(id),
	created_at DATETIME,
  	updated_at DATETIME
)ENGINE=InnoDB;
