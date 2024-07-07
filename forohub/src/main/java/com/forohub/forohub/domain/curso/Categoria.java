package com.forohub.forohub.domain.curso;

public enum Categoria {
    BACKEND("Backend"),
    FRONTEND("Frontend"),
    DATA_ANALYSIS("Análisis_datos"),
    MACHINE_LEARNING("Aprendizaje_artificial"),
    USER_DESIGN("Diseño de usuario");

    private String categoriaSpanish;

    Categoria(String categoriaSpanish) {
        this.categoriaSpanish = categoriaSpanish;
    }
}
