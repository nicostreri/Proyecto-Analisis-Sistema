create table scores(  
	id INTEGER AUTO_INCREMENT PRIMARY KEY,
    	amount_points INTEGER,
	correct_predicted_matches INTEGER,
	username_player varchar(128),
	bet_id INTEGER,
	CONSTRAINT control_player_score FOREIGN KEY (username_player) REFERENCES players(username),
	CONSTRAINT control_bet_score FOREIGN KEY (bet_id) REFERENCES bets (id),
  	created_at DATETIME,
  	updated_at DATETIME
)ENGINE=InnoDB;
