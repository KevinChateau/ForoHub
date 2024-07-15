package com.forohub.forohub.domain.respuesta;

import com.forohub.forohub.domain.topico.Topico;

import java.time.LocalDateTime;

public record DatosRespuestaRespuesta(Long id,
                                      String mensaje,
                                      String topicoName,
                                      LocalDateTime fechaCreacion,
                                      String autorName) {
}
