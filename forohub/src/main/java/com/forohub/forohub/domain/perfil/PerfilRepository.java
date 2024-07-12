package com.forohub.forohub.domain.perfil;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PerfilRepository extends JpaRepository<Perfil,Long> {

    Optional<Perfil> findByNombreIgnoreCase(String nombre);
}
