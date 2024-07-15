package com.forohub.forohub.infra.security;

import com.forohub.forohub.domain.usuario.Usuario;
import com.forohub.forohub.domain.usuario.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AutentificacionService implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        var usuario = usuarioRepository.findByEmail(email);
        System.out.println("AutentificacionService: " + usuario);
//        System.out.println("AutentificacionService: " + usuario + " -> " + usuario.getUsername() + " - " + usuario.getPassword());
        var myUser = new CustomUserDetails(usuario);
        System.out.println(myUser);
        System.out.println(myUser.getUsername() + " - " + myUser.getPassword());
        return  myUser;
    }
}
