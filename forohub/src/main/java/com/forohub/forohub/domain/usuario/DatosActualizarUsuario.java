package com.forohub.forohub.domain.usuario;

import jakarta.validation.constraints.NotNull;

public record DatosActualizarUsuario(@NotNull Long id,
                                     String nombre,
                                     String password) {
}
