create table bets(
	id INTEGER AUTO_INCREMENT PRIMARY KEY,
	bet_date DATETIME,
	schedule_id INTEGER,
	username_player varchar(128),
	CONSTRAINT control_schedule_bet FOREIGN KEY (schedule_id) REFERENCES schedules(id),
	CONSTRAINT control_usuario_bet FOREIGN KEY (username_player) REFERENCES users(username),
	create_at DATETIME,
	update_at DATETIME
)ENGINE=InnoDB;
