create table betresults( 
	--id INTEGER AUTO_INCREMENT PRIMARY KEY,
	id_bet INTEGER,
	id_result INTEGER,	
	id_prediction INTEGER,
	PRIMARY KEY(id_bet,id_result),
	CONSTRAINT control_bet_bt FOREIGN KEY (id_bet) REFERENCES bets(id),
	CONSTRAINT control_result_bt FOREIGN KEY (id_result) REFERENCES results(id),
	CONSTRAINT control_prediction_bt FOREIGN KEY (id_prediction) REFERENCES predictions(id),
	created_at DATETIME,
  	updated_at DATETIME
)ENGINE=InnoDB;
