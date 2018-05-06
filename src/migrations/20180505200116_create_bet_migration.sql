create table bets(
	id INTEGER AUTO_INCREMENT PRIMARY KEY,
	fecha_apuesta DATETIME,
	id_schedule INTEGER,
	id_user INTEGER,
	CONSTRAINT control_schedule_bet FOREIGN KEY (id_schedule) REFERENCES schedules(id),
	CONSTRAINT control_usuario_bet FOREIGN KEY (id_user) REFERENCES users(id),
	create_at DATETIME,
	update_at DATETIME
)ENGINE=InnoDB;
