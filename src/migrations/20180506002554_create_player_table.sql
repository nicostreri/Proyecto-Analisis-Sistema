create table players (
	id_user_player INTEGER,
	puntaje_total INTEGER,
	CONSTRAINT control_user_player FOREIGN KEY (id_user_player) REFERENCES users(id),
	created_at DATETIME,
  	updated_at DATETIME
)ENGINE=InnoDB;