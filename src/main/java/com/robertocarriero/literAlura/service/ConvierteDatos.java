package com.robertocarriero.literAlura.service;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.robertocarriero.literAlura.model.DatosLibros;
import com.robertocarriero.literAlura.model.Libros;
import com.robertocarriero.literAlura.model.DatosAutor;

import java.util.stream.Collectors;

public class ConvierteDatos implements  IConvierteDatos {
    private ObjectMapper objectMapper = new ObjectMapper();


    @Override
    public <T> T obtenerDatos(String json, Class<T> clase) {
        try {
            return objectMapper.readValue(json, clase);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

    }

    // Método para convertir DatosLibros a Libros
    public static Libros convertirADatosLibros(DatosLibros datosLibros) {
        String titulo = datosLibros.titulo();
        String autor = datosLibros.autor().stream()
                .map(DatosAutor::nombre)
                .collect(Collectors.joining(", "));
        String idiomas = String.join(", ", datosLibros.idiomas());
        double numeroDeDescargas = datosLibros.numeroDeDescargas();

        // Obtenemos el primer autor si existe, y extraemos las fechas
        Integer fechaNacimiento = null;
        Integer fechaFallecimiento = null;
        if (!datosLibros.autor().isEmpty()) {
            DatosAutor primerAutor = datosLibros.autor().get(0);
            fechaNacimiento = primerAutor.fechaNacimiento();
            fechaFallecimiento = primerAutor.fechaFallecimiento();
        }
        return new Libros(titulo, autor, idiomas, numeroDeDescargas, fechaNacimiento, fechaFallecimiento);
    }
}




