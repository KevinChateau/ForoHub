package com.forohub.forohub.domain.curso;


/*Se crea la proyección para poder recibir los atributos de Curso en el Repository
* sin la necesidad de traer los atributos de sus entidades relacionadas*/
public interface CursoProjection {
    Long getId();
    String getNombre();
    Categoria getCategoria();

}
