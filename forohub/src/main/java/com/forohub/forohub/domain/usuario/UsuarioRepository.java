package com.forohub.forohub.domain.usuario;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario,Long> {


    @Query("SELECT u.id, u.nombre, u.email, u.password FROM Usuario u WHERE email=:email")
    Optional<UsuarioProjection> findByEmail(String email);
}
