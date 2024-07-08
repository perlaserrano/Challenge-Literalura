package com.perla.curso.alurachallenge.dto;

import com.perla.curso.alurachallenge.models.Autor;

public record LibroDTO(int idLibro,
                       String titulo,
                       Autor autor,
                       String idioma,
                       int numeroDeDescargas
) {
}