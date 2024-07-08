package com.perla.curso.alurachallenge;

import com.perla.curso.alurachallenge.principal.MenuPrincipal;
import com.perla.curso.alurachallenge.repositorio.AutorRepository;
import com.perla.curso.alurachallenge.repositorio.LibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LiterAluraApplication implements CommandLineRunner {

    @Autowired
    private LibroRepository libroRepository;
    @Autowired
    private AutorRepository autorRepository;

    public static void main(String[] args) {
        SpringApplication.run(LiterAluraApplication.class, args);

    }
    @Override
    public void run(String... args) throws Exception {
        System.out.println("Iniciando la aplicación...");
        System.out.println("LibroRepository: " + libroRepository); // Verifica que no sea null
        System.out.println("AutorRepository: " + autorRepository); // Verifica que no sea null

        MenuPrincipal menuPrincipal = new MenuPrincipal(libroRepository, autorRepository);
        menuPrincipal.muestraElMenu();
    }


}