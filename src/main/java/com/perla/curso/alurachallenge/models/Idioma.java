package com.perla.curso.alurachallenge.models;

import java.util.Arrays;

public enum Idioma {

    EN("en", "Inglés"),
    ES("es", "Español");

    private final String iniciales;
    private final String nombreCompleto;

    Idioma(String iniciales, String nombreCompleto) {
        this.iniciales = iniciales;
        this.nombreCompleto = nombreCompleto;
    }

    public static String from(String iniciales) {
        for (Idioma idioma : values()) {
            if (idioma.iniciales.equalsIgnoreCase(iniciales)) {
                return idioma.nombreCompleto;
            }
        }
        throw new IllegalArgumentException("Idioma no encontrado: " + iniciales);
    }

//    public static Idioma obtenerIdiomaPorIniciales(String iniciales) {
//        for (Idioma idioma : values()) {
//            if (idioma.iniciales.equalsIgnoreCase(iniciales)) {
//                return idioma;
//            }
//        }
//        throw new IllegalArgumentException("Idioma no encontrado: " + iniciales);
//    }

    public static String[] opcionesDeIdiomas() {
        return Arrays.stream(values())
                .map(idioma -> idioma.iniciales + " - " + idioma.nombreCompleto)
                .toArray(String[]::new);
    }

    public static Idioma obtenerIdiomaPorIndice(int indice) {
        if (indice < 1 || indice > values().length) {
            throw new IllegalArgumentException("Índice fuera de rango: " + indice);
        }
        return values()[indice - 1];
    }

    public String getIniciales() {
        return iniciales;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }
}
