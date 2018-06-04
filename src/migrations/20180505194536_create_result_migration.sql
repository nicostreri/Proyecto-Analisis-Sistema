create table results(
	id INTEGER AUTO_INCREMENT PRIMARY KEY,
	result_type ENUM('empate', 'gana_local','gana_visitante','no_jugado') NOT NULL DEFAULT 'no_jugado',
	local_goals INTEGER,
	visit_goals INTEGER,
	create_at DATETIME,
	update_at DATETIME
)ENGINE=InnoDB;
