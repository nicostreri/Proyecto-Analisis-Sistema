create table players (
	username varchar(128) PRIMARY KEY,
	CONSTRAINT control_user_player FOREIGN KEY (username) REFERENCES users(username),
	created_at DATETIME,
  	updated_at DATETIME
)ENGINE=InnoDB;
