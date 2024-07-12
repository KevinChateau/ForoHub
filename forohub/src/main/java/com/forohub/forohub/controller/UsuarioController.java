package com.forohub.forohub.controller;

import com.forohub.forohub.domain.perfil.Perfil;
import com.forohub.forohub.domain.usuario.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping
    public ResponseEntity<DatosRespuestaCreacionUsuario> registrarTopico(@RequestBody @Valid DatosRegistroUsuario datosRegistroUsuario,
                                                                         UriComponentsBuilder uriComponentsBuilder) {
//        System.out.println("El request llega correctamente");

        Perfil perfil = usuarioService.registrarPerfilUsuario(datosRegistroUsuario);
        Usuario usuario = new Usuario(datosRegistroUsuario);
        usuario.setPerfiles(Collections.singletonList(perfil));

        Usuario usuarioSaved = usuarioRepository.save(usuario);

        DatosRespuestaCreacionUsuario datosRespuestaCreacionUsuario = new DatosRespuestaCreacionUsuario(usuarioSaved.getId(),usuarioSaved.getNombre(),usuarioSaved.getEmail());
        URI uri = uriComponentsBuilder.path("/usuario/{id}").buildAndExpand(usuarioSaved.getId()).toUri();

        System.out.println(usuarioSaved.getNombre() + ", " + usuarioSaved.getEmail() + " creado");

        return ResponseEntity.created(uri).body(datosRespuestaCreacionUsuario);
    }

    @GetMapping
    public ResponseEntity<List<Usuario>> listadoUsuarios() {
        var usuarios = usuarioRepository.findAll();
        return ResponseEntity.ok(usuarios);
    }

    @PutMapping
    @Transactional
    public ResponseEntity actualizarUsuario(@RequestBody @Valid DatosActualizarUsuario datosActualizarUsuario) {
        Usuario usuario = usuarioRepository.getReferenceById(datosActualizarUsuario.id());
        usuario.actualizarDatos(datosActualizarUsuario);

        return ResponseEntity.ok(new DatosRespuestaCreacionUsuario(usuario.getId(), usuario.getNombre(), usuario.getEmail()));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity eliminarUsuario(@PathVariable Long id) {
        Usuario usuario = usuarioRepository.getReferenceById(id);
        usuarioRepository.delete(usuario);
        return ResponseEntity.noContent().build();
    }


}
