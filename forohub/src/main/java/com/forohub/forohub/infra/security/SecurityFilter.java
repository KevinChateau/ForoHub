package com.forohub.forohub.infra.security;

import com.forohub.forohub.domain.usuario.UsuarioRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/*
 *El tipo de autorización que se utiliza en Insomnia es "Bearer Token".
 * Allí se coloca el Token devuelto al realizar el login
 * */
@Component
public class SecurityFilter extends OncePerRequestFilter {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        System.out.println("El filtro personalizado está siendo llamado");

        //Obtener el token del header
        //Por defecto, el token retorna el prefijo Bearer, para eliminarlo:
        var authHeaeder = request.getHeader("Authorization");//.replace("Bearer","");
        System.out.println("authHeaeder = " + authHeaeder);

        if (authHeaeder != null) {
            var token = authHeaeder.replace("Bearer ", "");
            //Verificando
            var emailUsuario = tokenService.getSubject(token);
            if (emailUsuario != null) { //Si no es nulo, entonces es válido
                //**************
                var usuario = new CustomUserDetails(usuarioRepository.findByEmail(emailUsuario)); //Retornar usuario
                //Forzamos un inicio de sesión
                var authentication = new UsernamePasswordAuthenticationToken(usuario, null, usuario.getAuthorities());
                //Para mantener activo el usuario con login
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }

        //Se debe mandar el request al siguiente filtro en la cadena de filtros
        filterChain.doFilter(request,response);
    }
}
