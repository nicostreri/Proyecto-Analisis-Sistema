CREATE TABLE users (
  username VARCHAR(128) PRIMARY KEY,
  name VARCHAR(128),
  lastname VARCHAR(128),
  password VARCHAR(128),
  created_at DATETIME,
  updated_at DATETIME
)ENGINE=InnoDB;
