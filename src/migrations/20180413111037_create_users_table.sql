CREATE TABLE users (
  username VARCHAR(128) PRIMARY KEY,
  nombre VARCHAR(128),
  apellido VARCHAR(128),
  password VARCHAR(128),
  created_at DATETIME,
  updated_at DATETIME
)ENGINE=InnoDB;
