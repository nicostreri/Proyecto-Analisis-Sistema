create table scores(  
	id integer auto_increment primary key,
        cant_puntos integer,
	partidos_acertados integer,
	id_user integer,
	id_bet integer,
	constraint control_user foreign key (id_user) references users(id),
	constraint control_get foreign key (id_bet) references bets (id),
  	created_at DATETIME,
  	updated_at DATETIME
)ENGINE=InnoDB;
