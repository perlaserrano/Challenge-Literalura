package com.perla.curso.alurachallenge.repositorio;
import com.perla.curso.alurachallenge.models.Autor;
import com.perla.curso.alurachallenge.models.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;

@Repository
public interface LibroRepository extends JpaRepository<Libro, Long> {
    List<String> findDistinctIdiomaBy();
    @Query("SELECT l FROM Libro l ORDER BY l.totalDeDescargas DESC LIMIT 10")
    List<Libro> findTop10Descargados();


    @Query("SELECT l FROM Libro l WHERE l.titulo = :title")
    Optional<Libro> findByTitulo(@Param("title") String title);

    Optional<Libro> findById(long id);
    List<Libro> findByAutorIdAutor(Long idAutor);



    Optional<Libro> findLibroByTitulo(String titulo);

    List<Libro> findByIdioma(String idioma);

    @Query("SELECT DISTINCT l.idioma FROM Libro l WHERE l.idioma IS NOT NULL")
    List<String> findDistinctIdiomas();

    @Query("SELECT l FROM Libro l WHERE l.autor = :autor")
    List<Libro> findLibrosByAutor(@Param("autor") Autor autor);
}