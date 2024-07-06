CREATE TABLE usuarios (

  id INT NOT NULL AUTO_INCREMENT,
  nombre VARCHAR(100) NOT NULL,
  email VARCHAR(100) NOT NULL UNIQUE,
  password VARCHAR(100) NOT NULL,
  perfil_id INT NOT NULL,

  PRIMARY KEY (id),

  CONSTRAINT fk_perfil
    FOREIGN KEY(perfil_id) REFERENCES perfiles(id)

);