create table scores(  
	id INTEGER AUTO_INCREMENT PRIMARY KEY,
    cant_puntos INTEGER,
	partidos_acertados INTEGER,
	username_player varchar(128),
	id_bet INTEGER,
	CONSTRAINT control_player_score FOREIGN KEY (username_player) REFERENCES players(username),
	CONSTRAINT control_bet_score FOREIGN KEY (id_bet) REFERENCES bets (id),
  	created_at DATETIME,
  	updated_at DATETIME
)ENGINE=InnoDB;