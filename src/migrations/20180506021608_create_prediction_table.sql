create table predictions(
	id INTEGER AUTO_INCREMENT PRIMARY KEY,
	result_type ENUM('empate', 'gana_local','gana_visitante') NOT NULL,
	hit BOOL DEFAULT NULL, -- Null porque no ha sido calculado
	score_id INTEGER,
	CONSTRAINT control_score_prediction FOREIGN KEY (score_id) REFERENCES scores (id),
  	created_at DATETIME,
  	updated_at DATETIME
)ENGINE=InnoDB;
