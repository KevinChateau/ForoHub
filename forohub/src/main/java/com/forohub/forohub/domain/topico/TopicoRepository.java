package com.forohub.forohub.domain.topico;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface TopicoRepository extends JpaRepository<Topico, Long> {


    Optional<Topico> findByTitulo(String titulo);

    Optional<Topico> findByMensaje(String mensaje);

    @Modifying
    @Transactional
    @Query("DELETE FROM Topico t WHERE t.id = :id")
    void deleteById(@Param("id") Long id);
}
