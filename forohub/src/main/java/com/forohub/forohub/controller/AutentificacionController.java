package com.forohub.forohub.controller;

import com.forohub.forohub.domain.usuario.DatosRegistroUsuario;
import com.forohub.forohub.domain.usuario.LogUsuario;
import com.forohub.forohub.domain.usuario.Usuario;
import com.forohub.forohub.infra.security.CustomUserDetails;
import com.forohub.forohub.infra.security.DatosJWTToken;
import com.forohub.forohub.infra.security.TokenService;
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

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity autentificadorLogin(@RequestBody @Valid LogUsuario logUsuario) {
        System.out.println(logUsuario);
        System.out.println(logUsuario.getEmail() + " - " + logUsuario.getPassword());
        Authentication authtoken = new UsernamePasswordAuthenticationToken(logUsuario.getEmail(),
                logUsuario.getPassword());
        //Se autentifica el usuario y clave pasados en el path
        System.out.println("authtoken = " + authtoken);
        var usuarioAutenticado = authenticationManager.authenticate(authtoken);
        System.out.println("usuarioAutenticado = " + usuarioAutenticado);
        var JWTtoken = tokenService.generarToken((CustomUserDetails) usuarioAutenticado.getPrincipal()); //Usuario que ya fue autenticado
        return ResponseEntity.ok(new DatosJWTToken(JWTtoken));

    }

}
