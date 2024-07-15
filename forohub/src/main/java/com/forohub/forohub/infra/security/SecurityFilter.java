package com.forohub.forohub.infra.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/*
 *El tipo de autorización que se utiliza en Insomnia es "Bearer Token".
 * Allí se coloca el Token devuelto al realizar el login
 * */
@Component
public class SecurityFilter extends OncePerRequestFilter {

    /*@Autowired
    private UsuarioRepository usuarioRepository;*/

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        System.out.println("El filtro personalizado está siendo llamado");

        //Se debe mandar el request al siguiente filtro en la cadena de filtros
        filterChain.doFilter(request,response);
    }
}
