create table predictions(
	id INTEGER AUTO_INCREMENT PRIMARY KEY,
	tipo_result ENUM('empate', 'gana_local','gana_visitante','no_jugado') NOT NULL DEFAULT 'no_jugado',
	cant_gol_local INTEGER,
	cant_gol_visit INTEGER,
	puntos_obtenidos INTEGER DEFAULT -1, -- -1 porque no ha sido calculado
	id_score INTEGER,
	CONSTRAINT control_score_prediction FOREIGN KEY (id_score) REFERENCES scores (id),
  	created_at DATETIME,
  	updated_at DATETIME
)ENGINE=InnoDB;