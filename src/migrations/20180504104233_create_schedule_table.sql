create table schedules(
	id INTEGER AUTO_INCREMENT PRIMARY KEY,
	nombre_fecha VARCHAR(30),
	id_fixture INTEGER,
	CONSTRAINT control_fixture_schedule FOREIGN KEY (id_fixture) REFERENCES fixtures (id),
	create_at DATETIME,
	update_at DATETIME
)ENGINE=InnoDB;