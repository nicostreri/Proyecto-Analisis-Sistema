create table bets(
	id INTEGER AUTO_INCREMENT PRIMARY KEY,
	fecha_apuesta DATETIME,
	id_schedule INTEGER,
	username_player varchar(128),
	CONSTRAINT control_schedule_bet FOREIGN KEY (id_schedule) REFERENCES schedules(id),
	CONSTRAINT control_usuario_bet FOREIGN KEY (username_player) REFERENCES users(username),
	create_at DATETIME,
	update_at DATETIME
)ENGINE=InnoDB;
