package com.forohub.forohub.domain.usuario.validaciones;

import com.forohub.forohub.domain.usuario.DatosRegistroUsuario;
import com.forohub.forohub.domain.usuario.UsuarioProjection;
import com.forohub.forohub.domain.usuario.UsuarioRepository;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class DuplicidadCorreo implements ValidadorUsuarios{

    @Autowired
    private UsuarioRepository usuarioRepository;

    public void validar(DatosRegistroUsuario datosRegistroUsuario) {
        Optional<UsuarioProjection> usuario = usuarioRepository.findByEmailProjection(datosRegistroUsuario.email());
        if (usuario.isPresent()) {
            throw new ValidationException("Correo electrónica ya existente en la base de datos");
        }
    }

}
