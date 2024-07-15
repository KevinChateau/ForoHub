package com.forohub.forohub.controller;

import com.forohub.forohub.domain.usuario.DatosRegistroUsuario;
import com.forohub.forohub.domain.usuario.LogUsuario;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.ConnectionBuilder;

@RestController
@RequestMapping("/login")
public class AutentificacionController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping
    public ResponseEntity autentificadorLogin(@RequestBody @Valid LogUsuario logUsuario) {
        System.out.println("logUsuario = " + logUsuario);
        System.out.println(logUsuario.getEmail() + " - " + logUsuario.getPassword());
        try {
            ConnectionBuilder datosRegistroUsuario;
            Authentication authtoken = new UsernamePasswordAuthenticationToken(logUsuario.getEmail(), logUsuario.getPassword());
            System.out.println(logUsuario +" - " + authtoken);
            Authentication usuarioAutenticado = authenticationManager.authenticate(authtoken);
            System.out.println("usuarioAutenticado = " + usuarioAutenticado);
            return ResponseEntity.ok(usuarioAutenticado);
        } catch (AuthenticationException e) {
            return ResponseEntity.status(401).body("Credenciales inválidas");
        }

    }

}
