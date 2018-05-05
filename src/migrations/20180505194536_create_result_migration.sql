create table result(
	id integer auto_increment primary key,
	tipo_result ENUM('empate', 'gana_local','gana_visitante','no_jugado') NOT NULL DEFAULT 'no_jugado',
	cant_goles_local integer,
	cant_goles_visit integer,
	create_at DATETIME,
	update_at DATETIME
)ENGINE=InnoDB;