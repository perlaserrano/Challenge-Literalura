package com.perla.curso.alurachallenge.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Map;

@Service
public class ConsumoApi {
    private String urlGutendex = "https://gutendex.com/books/?search=";
    private Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public String buscarLibro(String tituloLibro) {
        String url = urlGutendex + tituloLibro.replace(" ", "%20");

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            return response.body();
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException("Error al obtener el libro desde la API", e);
        }
    }

    public String getIdioma(Map<String, Object> jsonMap) {
        // Verifica si el mapa contiene la clave "language"
        if (jsonMap.containsKey("language")) {
            return (String) jsonMap.get("language");
        } else {
            return null; // o algún valor por defecto si no se encuentra
        }
    }

}
