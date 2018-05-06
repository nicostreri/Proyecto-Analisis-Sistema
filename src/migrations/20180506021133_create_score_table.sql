create table scores(  
	id INTEGER AUTO_INCREMENT PRIMARY KEY,
    cant_puntos INTEGER,
	partidos_acertados INTEGER,
	id_player INTEGER,
	id_bet INTEGER,
	CONSTRAINT control_player_score FOREIGN KEY (id_player) REFERENCES players(id_user_player),
	CONSTRAINT control_bet_score FOREIGN KEY (id_bet) REFERENCES bets (id),
  	created_at DATETIME,
  	updated_at DATETIME
)ENGINE=InnoDB;