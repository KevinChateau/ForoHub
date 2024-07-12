package com.forohub.forohub.domain.usuario;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DatosRegistroUsuario(@NotBlank String nombre,
                                   @Email String email,
                                   @NotNull String password,
                                   @NotNull String perfil) {
}
