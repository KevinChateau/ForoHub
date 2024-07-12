package com.forohub.forohub.domain.usuario;

import com.forohub.forohub.domain.perfil.Perfil;
import com.forohub.forohub.domain.perfil.PerfilRepository;
import com.forohub.forohub.domain.usuario.validaciones.ValidadorUsuarios;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioService {
    @Autowired
    private ValidadorUsuarios validadorUsuarios;

    @Autowired
    private PerfilRepository perfilRepository;

    public Perfil registrarPerfilUsuario(DatosRegistroUsuario datosRegistroUsuario) {
        validadorUsuarios.validar(datosRegistroUsuario);

        Optional<Perfil> perfil =  perfilRepository.findByNombreIgnoreCase(datosRegistroUsuario.perfil());

        if (perfil.isPresent()) {
            return perfil.get();
        }else {
            throw new ValidationException("Perfil no encontrado");
        }

    }
}
