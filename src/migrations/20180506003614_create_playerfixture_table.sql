create table playerfixture (	
	id INTEGER AUTO_INCREMENT PRIMARY KEY,
	id_player INTEGER,
	id_fixture INTEGER,
	CONSTRAINT control_player_suscripcion FOREIGN KEY (id_player) REFERENCES players(id_user_player),
	CONSTRAINT control_fixture_suscripcion FOREIGN KEY (id_fixture) REFERENCES fixtures(id),
	created_at DATETIME,
  	updated_at DATETIME
)ENGINE=InnoDB;