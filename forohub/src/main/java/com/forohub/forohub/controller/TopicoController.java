package com.forohub.forohub.controller;


import com.forohub.forohub.domain.topico.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/topicos")
public class TopicoController {

    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    private TopicoService topicoService;


    @PostMapping
    public ResponseEntity<DatosRespuestaTopico> registrarTopico(@RequestBody @Valid DatosRegistroTopico datosRegistroTopico,
                                                                UriComponentsBuilder uriComponentsBuilder) {

//        System.out.println("El request llega correctamente");

        Topico topico = topicoService.saveTopicoInDB(datosRegistroTopico);

        DatosRespuestaTopico datosRespuestaTopico = new DatosRespuestaTopico(topico.getId(), topico.getTitulo(),
                topico.getMensaje(), topico.getFechaCreacion(), topico.getStatus(), topico.getUsuario().getNombre(), topico.getCurso().getNombre());

        URI uri = uriComponentsBuilder.path("/topico/{id}").buildAndExpand(topico.getId()).toUri();

        System.out.println("Tópico: " + topico.getTitulo() + " creada");

        return ResponseEntity.created(uri).body(datosRespuestaTopico);
    }

    @GetMapping
    public ResponseEntity<List<Topico>> listadoTopicos() {
        var topicos = topicoRepository.findAll();
        return ResponseEntity.ok(topicos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DatosRespuestaTopico> retornaDatosTopico(@PathVariable Long id) {

        Topico topico = topicoService.getTopicoById(id);
        var datosTopicos = new DatosRespuestaTopico(topico.getId(), topico.getTitulo(), topico.getMensaje(),
                topico.getFechaCreacion(), topico.getStatus(), topico.getUsuario().getNombre(), topico.getCurso().getNombre());
        return ResponseEntity.ok(datosTopicos);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity actualizarTopico(@PathVariable Long id, @RequestBody @Valid DatosActualizarTopico datosActualizarTopico) {
        Topico topico = topicoService.getTopicoById(id);
        topicoService.validarActualizacionTopicos(new DatosRegistroTopico(datosActualizarTopico.titulo(), datosActualizarTopico.mensaje(),
                topico.getUsuario().getNombre(), topico.getCurso().getNombre()));
        topico.actualizarDatos(datosActualizarTopico);

        return ResponseEntity.ok(new DatosRespuestaTopico(topico.getId(), topico.getTitulo(), topico.getMensaje(),
                topico.getFechaCreacion(), topico.getStatus(), topico.getUsuario().getNombre(), topico.getCurso().getNombre()));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity eliminarTopico(@PathVariable Long id) {
        Topico topico = topicoService.getTopicoById(id); //Valida que exista el id
        System.out.println(topico.getId());
        topicoRepository.deleteById(topico.getId());
        return ResponseEntity.noContent().build();

    }


}
