package com.forohub.forohub.domain.respuesta;

import com.forohub.forohub.domain.curso.Curso;
import com.forohub.forohub.domain.topico.Topico;
import com.forohub.forohub.domain.usuario.Usuario;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Table(name = "respuestas")
@Entity
//Lombok
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Respuesta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String mensaje;
    private LocalDateTime fechaCreacion;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "topico_id")
    private Topico topico;

    public Respuesta(DatosRegistroRespuesta datosRegistroRespuesta) {
        this.mensaje = datosRegistroRespuesta.mensaje();
        this.fechaCreacion = LocalDateTime.now();
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public void setTopico(Topico topico) {
        this.topico = topico;
    }
}
