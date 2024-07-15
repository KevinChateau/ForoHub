package com.forohub.forohub.domain.curso;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface CursoRepository extends JpaRepository<Curso,Long> {

    @Query("SELECT c.id, c.nombre, c.categoria FROM Curso c WHERE c.nombre =:nombre")
    Optional<CursoProjection> findByNombreNative(String nombre);

    Optional<Curso> findByNombreIgnoreCase(String cursoName);
}
