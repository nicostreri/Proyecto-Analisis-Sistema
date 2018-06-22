create table schedules(
	id INTEGER AUTO_INCREMENT PRIMARY KEY,
	date_name VARCHAR(30),
	fixture_id INTEGER,
	calculated BOOL NOT NULL DEFAULT FALSE,
	CONSTRAINT control_fixture_schedule FOREIGN KEY (fixture_id) REFERENCES fixtures (id),
	create_at DATETIME,
	update_at DATETIME
)ENGINE=InnoDB;
