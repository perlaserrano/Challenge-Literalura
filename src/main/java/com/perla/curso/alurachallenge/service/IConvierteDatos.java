package com.perla.curso.alurachallenge.service;

public interface IConvierteDatos {
    <T> T obtenerDatos(String json, Class<T> clase);

}