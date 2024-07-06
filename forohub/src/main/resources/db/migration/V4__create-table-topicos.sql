CREATE TABLE topicos (

  id INT NOT NULL AUTO_INCREMENT,
  titulo VARCHAR(100) NOT NULL,
  mensaje MEDIUMTEXT NOT NULL,
  fecha_creacion DATETIME NOT NULL,
  status VARCHAR(100) NOT NULL,
  curso_id INT NOT NULL,
  usuario_id INT NOT NULL,

  PRIMARY KEY (id),

  CONSTRAINT fk_curso
    FOREIGN KEY(curso_id) REFERENCES cursos(id),

   CONSTRAINT fk_usuario
    FOREIGN KEY (usuario_id) REFERENCES usuarios(id)

);