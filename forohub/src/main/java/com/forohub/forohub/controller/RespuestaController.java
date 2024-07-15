package com.forohub.forohub.controller;

import com.forohub.forohub.domain.respuesta.*;
import com.forohub.forohub.domain.topico.DatosRegistroTopico;
import com.forohub.forohub.domain.topico.DatosRespuestaTopico;
import com.forohub.forohub.domain.topico.Topico;
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
@RequestMapping("/respuestas")
public class RespuestaController {

    @Autowired
    private RespuestaRepository respuestaRepository;

    @Autowired
    private RespuestaService respuestaService;

    @PostMapping
    public ResponseEntity<DatosRespuestaRespuesta> registrarTopico(@RequestBody @Valid DatosRegistroRespuesta datosRegistroRespuesta,
                                                                   UriComponentsBuilder uriComponentsBuilder) {

        System.out.println("El request llega correctamente");
        Respuesta respuesta = respuestaService.saveRespuestaInDb(datosRegistroRespuesta);

        DatosRespuestaRespuesta datosRespuestaRespuesta = new DatosRespuestaRespuesta(respuesta.getId(),respuesta.getMensaje(),
                respuesta.getTopico().getTitulo(), respuesta.getFechaCreacion(), respuesta.getUsuario().getNombre());

        URI uri = uriComponentsBuilder.path("/respuestas/{id}").buildAndExpand(respuesta.getId()).toUri();

        System.out.println("Respuesta: " + respuesta.getMensaje() + " creada");

        return ResponseEntity.created(uri).body(datosRespuestaRespuesta);
    }

}
