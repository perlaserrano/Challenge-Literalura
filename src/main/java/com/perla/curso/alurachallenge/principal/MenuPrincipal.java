package com.perla.curso.alurachallenge.principal;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.perla.curso.alurachallenge.models.*;
import com.perla.curso.alurachallenge.repositorio.AutorRepository;
import com.perla.curso.alurachallenge.repositorio.LibroRepository;
import com.perla.curso.alurachallenge.service.AutorServices;
import com.perla.curso.alurachallenge.service.ConsumoApi;
import com.perla.curso.alurachallenge.service.ConvierteDatos;
import com.perla.curso.alurachallenge.service.LibroServices;
import org.jline.reader.LineReader;
import org.jline.reader.LineReaderBuilder;
import org.jline.terminal.Terminal;
import org.jline.terminal.TerminalBuilder;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collectors;

public class MenuPrincipal {
    public LibroRepository libroRepository;
    private AutorRepository autorRepository;
    private Scanner teclado = new Scanner(System.in);
    private ConsumoApi consumoApi = new ConsumoApi();
    private ConvierteDatos convierteDatos = new ConvierteDatos();
    private DatosLibro datosLibro;
    private DatosAutor datosAutor;
    private LibroServices libroServices = new LibroServices();
    private AutorServices autorServices = new AutorServices();

    private Autor autorDelLibro;
    private int opciones = -1;

    private List<Autor> autores;
    private List<Libro> libros;

    public MenuPrincipal(LibroRepository libroRepository, AutorRepository autorRepository) {
        this.autorRepository = autorRepository;
        this.libroRepository = libroRepository;
    }

    public void muestraElMenu() throws Exception {
        var textoMenu = """
                \nM E N U  P R I N C I P A L - L I T E R  A L U R A 
                
                [ 1 ] Buscar libro por título
                [ 2 ] Listar libros registrados
                [ 3 ] Listar autores registrados
                [ 4 ] Listar autores vivos en un determinado año
                [ 5 ] Listar libros por idioma
       
                [ 0 ] Salir
                \nElija una opción a través del número correspondiente:
                """;

        do {
            System.out.print(textoMenu);
            opciones = teclado.nextInt();
            teclado.nextLine(); // Limpiar el buffer

            switch (opciones) {
                case 1 -> obtenerLibroPorTitulo();
                case 2 -> listarLibrosRegistrados();
                case 3 -> listarAutoresRegistrados();
                case 4 -> listarAutoresVivosPorAno();
                case 5 -> listarLibrosPorIdioma();
                case 0 -> System.out.println("Hasta Luego");
                default -> System.out.println("Opción Inválida");
            }
        } while (opciones != 0);
    }

    private void obtenerLibroPorTitulo() throws Exception {
        // getDatosLibro();
        busquedaDeLibros();
    }

    private String getAutoresNombres(List<Autor> autores) {
        return autores.stream()
                .map(Autor::getNombre)
                .collect(Collectors.joining(", "));
    }

    private void listarLibrosRegistrados() {
        System.out.println("\nListado de libros registrados:");
        libros = libroRepository.findAll();

        if (libros.isEmpty()) {
            System.out.println("No hay libros registrados.");
        } else {
            for (Libro libro : libros) {
                mostrarDetallesLibro(libro);
            }
        }

        hacerPause();
    }

    private void listarAutoresRegistrados() {
        System.out.println("\nListado de autores registrados:");
        autores = autorRepository.findAll();
        autores.forEach(System.out::println);
        hacerPause();
    }



    private void listarAutoresVivosPorAno() {
        System.out.println("\nEscriba el año que desea buscar: ");
        int ano = teclado.nextInt();

        List<Autor> autoresVivos = autorRepository.findAutorByFecha(ano);

        if (autoresVivos.isEmpty()) {
            System.out.println("No hay autores vivos en ese año.");
        } else {
            System.out.println("\nListado de autores vivos en el año " + ano + ": ");
            autoresVivos.forEach(autor -> System.out.println("Nombre: " + autor.getNombre() + ", Fecha de Nacimiento: " + autor.getFechaNacimiento()));
        }

        hacerPause();
    }


    private void listarLibrosPorIdioma() {
        System.out.println("\nListado de idiomas registrados:");
        String[] opcionesIdiomas = Idioma.opcionesDeIdiomas();
        for (int i = 0; i < opcionesIdiomas.length; i++) {
            System.out.println("[" + (i + 1) + "] " + opcionesIdiomas[i]);
        }

        System.out.println("\nSeleccione el número del idioma que desea buscar: ");
        var eleccion = teclado.nextInt();
        teclado.nextLine(); // Limpia el buffer

        try {
            Idioma idiomaSeleccionado = Idioma.obtenerIdiomaPorIndice(eleccion);
            String idiomaSeleccionadoNombre = idiomaSeleccionado.getNombreCompleto();
            System.out.println("\nListado de libros en - [ " + idiomaSeleccionadoNombre.toUpperCase() + " ]:");
            var libros = libroRepository.findByIdioma(idiomaSeleccionado.getIniciales());
            if (libros.isEmpty()) {
                System.out.println("No hay libros registrados en este idioma.");
            } else {
                libros.forEach(System.out::println);
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Opción Inválida");
        }

        hacerPause();
    }


    public void hacerPause() {
        Terminal terminal = null;
        try {
            terminal = TerminalBuilder.builder().build();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        LineReader lineReader = LineReaderBuilder.builder().terminal(terminal).build();

        System.out.println("Presione cualquier tecla para continuar...");

        // Esperar a que el usuario presione cualquier tecla (no necesita Enter)
        lineReader.readLine();
    }

    private void busquedaDeLibros() throws Exception {
        System.out.println("Escriba el título del libro que desea buscar: ");
        var tituloLibro = teclado.nextLine();

        var resultadoBusqueda = new ConsumoApi().buscarLibro(tituloLibro);

        JSONObject jsonObject = new JSONObject(resultadoBusqueda);
        JSONArray resultsArray = jsonObject.getJSONArray("results");

        if (resultsArray.isEmpty()) {
            System.out.println("Libro no encontrado con el título: " + tituloLibro);
            hacerPause();
            return;
        }

        System.out.println("Se encontraron " + resultsArray.length() + " libros: \n");
        for (int i = 0; i < resultsArray.length(); i++) {
            System.out.println("[" + (i + 1) + "] " + resultsArray.getJSONObject(i).getString("title"));
        }

        System.out.println("\nSeleccione el libro deseado indicando su número, o presione 0 para cancelar: ");
        var numeroLibro = teclado.nextInt();
        teclado.nextLine(); // Limpiar el buffer
        if (numeroLibro == 0) {
            hacerPause();
            return;
        }
        numeroLibro = numeroLibro - 1;

        jsonObject = new JSONObject(resultsArray.getJSONObject(numeroLibro).toString());
        datosLibro = convierteDatos.obtenerDatos(jsonObject.toString(), DatosLibro.class);

        // Verificar si el libro ya está registrado
        Optional<Libro> optionalLibro = libroRepository.findById(datosLibro.idLibro());
        if (optionalLibro.isPresent()) {
            Libro libroEncontrado = optionalLibro.get();
            System.out.println("Libro ya está registrado:");
            mostrarDetallesLibro(libroEncontrado);
            hacerPause();
            return;
        }

        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> jsonMap = mapper.readValue(jsonObject.toString(), Map.class);
        String authorsJson = mapper.writeValueAsString((List<Map<String, Object>>) jsonMap.get("authors"));
        String resultado = authorsJson.substring(1, authorsJson.length() - 1);
        datosAutor = convierteDatos.obtenerDatos(resultado, DatosAutor.class);

        String idioma = new ConsumoApi().getIdioma(jsonMap);

        autores = autorRepository.findAll();
        Optional<Autor> optionalAutor = autores.stream()
                .filter(a -> a.getNombre().equals(datosAutor.nombre()))
                .findFirst();
        if (optionalAutor.isPresent()) {
            autorDelLibro = optionalAutor.get();
        } else {
            autorDelLibro = new Autor(datosAutor);
            autorRepository.save(autorDelLibro);
        }
        Libro libroNuevo = new Libro(datosLibro, autorDelLibro, idioma);
        libroRepository.save(libroNuevo);
        System.out.println("Libro registrado exitosamente:");
        mostrarDetallesLibro(libroNuevo); // Imprimir detalles del libro registrado
        hacerPause();
    }

    private void mostrarDetallesLibro(Libro libro) {
        System.out.println("*******Libro*********");
        System.out.println("Título: " + libro.getTitulo());
        System.out.println("Autor: " + libro.getAutor().getNombre());
        System.out.println("Idiomas: " + libro.getIdioma());
        System.out.println("Número de descargas: " + libro.getTotalDeDescargas());
        System.out.println("**********************");
    }


}
