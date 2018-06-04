create table bets_results( 
	--id INTEGER AUTO_INCREMENT PRIMARY KEY,
	bet_id INTEGER,
	result_id INTEGER,	
	prediction_id INTEGER,
	PRIMARY KEY(bet_id,result_id),
	CONSTRAINT control_bet_bt FOREIGN KEY (bet_id) REFERENCES bets(id),
	CONSTRAINT control_result_bt FOREIGN KEY (result_id) REFERENCES results(id),
	CONSTRAINT control_prediction_bt FOREIGN KEY (prediction_id) REFERENCES predictions(id),
	created_at DATETIME,
  	updated_at DATETIME
)ENGINE=InnoDB;
