package com.forohub.forohub.domain.topico;

import com.forohub.forohub.domain.curso.Curso;
import com.forohub.forohub.domain.usuario.Usuario;
import jakarta.validation.constraints.NotBlank;

public record DatosRegistroTopico(@NotBlank String titulo,
                                  @NotBlank String mensaje,
                                  @NotBlank String autor,
                                  @NotBlank String curso) {
}
