package com.forohub.forohub.domain.curso.validaciones;

import com.forohub.forohub.domain.curso.CursoProjection;
import com.forohub.forohub.domain.curso.DatosRegistroCurso;
import com.forohub.forohub.domain.curso.CursoRepository;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class DuplicidadPerfiles implements ValidadorCursos {

    @Autowired
    private CursoRepository cursoRepository;

    public void validar(DatosRegistroCurso datosRegistroCurso) {
        Optional<CursoProjection> curso = cursoRepository.findByNombreNative(datosRegistroCurso.nombre().toUpperCase());
        if (curso.isPresent()) {
            throw new ValidationException("Nombre ya existente en la base de datos");
        }
    }

}
