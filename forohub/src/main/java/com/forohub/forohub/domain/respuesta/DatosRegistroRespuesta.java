package com.forohub.forohub.domain.respuesta;

import jakarta.validation.constraints.NotBlank;

public record DatosRegistroRespuesta(@NotBlank  String mensaje,
                                     @NotBlank String topico,
                                     @NotBlank String autor
                                     ) {
}
