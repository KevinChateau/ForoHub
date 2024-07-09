package com.forohub.forohub.controller;


import com.forohub.forohub.domain.curso.Curso;
import com.forohub.forohub.domain.curso.DatosRegistroCurso;
import com.forohub.forohub.domain.curso.DatosRespuestaCreacionCurso;
import com.forohub.forohub.domain.curso.CursoRepository;
import com.forohub.forohub.domain.curso.validaciones.ValidadorCursos;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/curso")
public class CursoController {

    @Autowired
    private CursoRepository cursoRepository;

    @Autowired
    private ValidadorCursos validadorCursos;


    @PostMapping
    public ResponseEntity<DatosRespuestaCreacionCurso> registrarTopico(@RequestBody @Valid DatosRegistroCurso datosRegistroCurso,
                                                                       UriComponentsBuilder uriComponentsBuilder) {
//        System.out.println("El request llega correctamente");
        validadorCursos.validar(datosRegistroCurso);

        Curso curso = cursoRepository.save(new Curso(datosRegistroCurso));

        DatosRespuestaCreacionCurso datosRespuestaCreacionCurso = new DatosRespuestaCreacionCurso(curso.getId(), curso.getNombre(), curso.getCategoria());

        URI uri = uriComponentsBuilder.path("/curso/{id}").buildAndExpand(curso.getId()).toUri();

        System.out.println(curso.getNombre() + ", " + curso.getCategoria() + " creada");

        return ResponseEntity.created(uri).body(datosRespuestaCreacionCurso);
    }
}
