package com.forohub.forohub.domain.curso;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DatosRegistroCurso(@NotBlank(message = "Nombre es obligatorio") String nombre,
                                 @NotBlank
                                 String categoria){
}
