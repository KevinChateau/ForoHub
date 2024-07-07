package com.forohub.forohub.domain.usuario;


import com.forohub.forohub.domain.perfil.Perfil;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Table(name = "usuarios")
@Entity
//Lombok
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String correoElectronico;
    private String password;

    @ManyToMany
    @JoinTable( //Definir tabla de unión (tabla intermedia entre Usuario y Perfil)
            name = "usuario_perfil",
            joinColumns = @JoinColumn(name = "usuario_id"), // define la columna en la tabla de unión que hace referencia a la entidad propietaria de la relación (Usuario).
            inverseJoinColumns = @JoinColumn(name = "perfil_id")
    )
    private List<Perfil> perfiles;

}
