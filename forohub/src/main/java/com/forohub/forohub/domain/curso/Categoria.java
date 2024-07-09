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

    public static Categoria getCategoriaSpanish(String text) {
        for (Categoria categoria : Categoria.values()) {
            if (categoria.categoriaSpanish.equalsIgnoreCase(text)) {
                return categoria;
            }
        }
        throw new IllegalArgumentException("Ninguna categoría encontrada para " + text);
    }

}
