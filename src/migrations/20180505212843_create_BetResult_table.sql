create table betresults( 
	id integer auto_increment primary key,
		
	id_bet integer,
	id_result integer,	
	id_prediction integer,

	constraint control_bet foreign key (id_bet) references bets(id),
	constraint control_result foreign key (id_result) references results(id),
	constraint control_prediction foreign key (id_prediction) references predictions(id),
	created_at DATETIME,
  	updated_at DATETIME
)ENGINE=InnoDB;