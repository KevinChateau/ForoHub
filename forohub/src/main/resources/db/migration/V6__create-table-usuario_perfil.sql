CREATE TABLE usuario_perfil (

  usuario_id INT NOT NULL,
  perfil_id INT NOT NULL,
  PRIMARY KEY (usuario_id, perfil_id),

  CONSTRAINT fk_usuario_usuario FOREIGN KEY (usuario_id) REFERENCES usuarios(id),
  CONSTRAINT fk_perfil_perfil FOREIGN KEY (perfil_id) REFERENCES perfiles(id)

);
