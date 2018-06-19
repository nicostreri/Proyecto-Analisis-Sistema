create table administrators (
	username varchar(128) PRIMARY KEY,
	CONSTRAINT control_user_administrator FOREIGN KEY (username) REFERENCES users(username),
	created_at DATETIME,
  	updated_at DATETIME
)ENGINE=InnoDB;