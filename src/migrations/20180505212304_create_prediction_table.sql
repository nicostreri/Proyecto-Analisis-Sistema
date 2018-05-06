create table predictions(
	id integer auto_increment primary key,
	tipo_result ENUM('empate', 'gana_local','gana_visitante','no_jugado') NOT NULL DEFAULT 'no_jugado',
	cant_gol_local integer,
	cant_gol_visit integer,
	puntos_obtenidos integer default -1, -- -1 porque no ha sido calculado
	id_score integer,
	constraint control_score foreign key (id_score) references scores (id),
  	created_at DATETIME,
  	updated_at DATETIME
)ENGINE=InnoDB;