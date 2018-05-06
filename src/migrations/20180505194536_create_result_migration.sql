create table results(
	id INTEGER AUTO_INCREMENT PRIMARY KEY,
	tipo_result ENUM('empate', 'gana_local','gana_visitante','no_jugado') NOT NULL DEFAULT 'no_jugado',
	cant_goles_local INTEGER,
	cant_goles_visit INTEGER,
	create_at DATETIME,
	update_at DATETIME
)ENGINE=InnoDB;