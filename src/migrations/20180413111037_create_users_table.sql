CREATE TABLE users (
  id INTEGER AUTO_INCREMENT PRIMARY KEY,
  nombre VARCHAR(128),
  apellido VARCHAR(128),
  username VARCHAR(128),
  password VARCHAR(128),
  created_at DATETIME,
  updated_at DATETIME
)ENGINE=InnoDB;
