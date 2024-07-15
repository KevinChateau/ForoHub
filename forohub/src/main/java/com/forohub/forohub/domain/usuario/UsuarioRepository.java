package com.forohub.forohub.domain.usuario;

import com.forohub.forohub.domain.topico.Topico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;
import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario,Long> {


    @Query("SELECT u.id, u.nombre, u.email, u.password FROM Usuario u WHERE email=:email")
    Optional<UsuarioProjection> findByEmailProjection(String email);

    Optional<Usuario> findByNombreIgnoreCase(String autor);

    @Query("SELECT new com.forohub.forohub.domain.usuario.UsuarioDTO(u.id, u.nombre, u.email, u.password) FROM Usuario u")
    List<UsuarioDTO> findAllUsers();

//    Usuario findByEmail(String email);

    UserDetails findByEmail(String username);
}
