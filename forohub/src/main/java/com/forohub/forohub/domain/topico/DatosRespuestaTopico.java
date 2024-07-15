package com.forohub.forohub.domain.topico;

import com.forohub.forohub.domain.curso.Curso;
import com.forohub.forohub.domain.usuario.Usuario;

import java.time.LocalDateTime;

public record DatosRespuestaTopico(Long id,
                                   String titulo,
                                   String mensaje,
                                   LocalDateTime fechaCreacion,
                                   String status,
                                   String usuario,
                                   String curso) {
}
