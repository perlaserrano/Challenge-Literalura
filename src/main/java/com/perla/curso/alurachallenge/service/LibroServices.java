package com.perla.curso.alurachallenge.service;

import com.perla.curso.alurachallenge.models.Libro;
import com.perla.curso.alurachallenge.models.Autor; // Asegúrate de importar el modelo Autor si lo necesitas
import com.perla.curso.alurachallenge.repositorio.AutorRepository;
import com.perla.curso.alurachallenge.repositorio.LibroRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
@Service
public class LibroServices {
    @Autowired
    public LibroRepository libroRepository;

    public List<String> obtenerIdiomasDistintos() {
        return libroRepository.findDistinctIdiomas();
    }

    public List<Libro> obtenerLibrosPorIdioma(String idioma) {
        return libroRepository.findByIdioma(idioma);
    }
    public Libro findLibroByTitulo(String titulo) throws Exception {
        return libroRepository.findByTitulo(titulo)
                .orElseThrow(() -> new Exception("Libro no encontrado con el título: " + titulo));
    }


}