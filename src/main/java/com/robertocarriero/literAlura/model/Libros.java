package com.robertocarriero.literAlura.model;


import jakarta.persistence.*;

import java.util.*;


@Entity
@Table(name = "libros")
public class Libros {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    @Column(unique = true)
    private String titulo;
    private String autor;
    private String idiomas;
    private double numeroDeDescargas;
    private Integer fechaNacimiento;
    private Integer fechaFallecimiento;
    private static final Map<String, String> NOMBRES_IDIOMAS = new HashMap<>();

    static {
        NOMBRES_IDIOMAS.put("en", "Inglés");
        NOMBRES_IDIOMAS.put("es", "Español");
        NOMBRES_IDIOMAS.put("fr", "Francés");
        NOMBRES_IDIOMAS.put("tl", "Tagalo");
        NOMBRES_IDIOMAS.put("pt", "Portugues");
        NOMBRES_IDIOMAS.put("it", "Italiano");

    }

    public Libros(String titulo, String autor, String idiomas,
                  double numeroDeDescargas, Integer fechaNacimiento, Integer fechaFallecimiento) {
        this.titulo = titulo;
        this.autor = autor;
        this.idiomas = idiomas;
        this.numeroDeDescargas = numeroDeDescargas;
        this.fechaNacimiento = fechaNacimiento;
        this.fechaFallecimiento = fechaFallecimiento;
    }


    public Libros() {
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public Integer getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Integer fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public Integer getFechaFallecimiento() {
        return fechaFallecimiento;
    }

    public void setFechaFallecimiento(Integer fechaFallecimiento) {
        this.fechaFallecimiento = fechaFallecimiento;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getIdiomas() {
        return idiomas;
    }

    public void setIdiomas(String idiomas) {
        this.idiomas = idiomas;
    }

    public double getNumeroDeDescargas() {
        return numeroDeDescargas;
    }

    public void setNumeroDeDescargas(double numeroDeDescargas) {
        this.numeroDeDescargas = numeroDeDescargas;
    }

    public void imprimirEnFormato() {
        System.out.println("**********   Titulo: " + titulo);
        System.out.println("**********   Autor: " + autor);
        System.out.println("**********   Fecha de Nacimiento del Autor : " + fechaNacimiento);
        System.out.println("**********   Fecha de Fallecimiento del Autor :" + fechaFallecimiento);
        System.out.println("**********   Idioma: " + idiomas);
        System.out.println("**********   Numero de descargas: " + numeroDeDescargas);

    }

    public void mostrarLibros() {
        System.out.println("Libros guardados\n");
        System.out.println("**********   Titulo: " + titulo);
        System.out.println("**********   Autor: " + autor);
        System.out.println("**********   Fecha de Nacimiento del Autor : " + fechaNacimiento);
        System.out.println("**********   Fecha de Fallecimiento del Autor :" + fechaFallecimiento);
        System.out.println("**********   Idioma: " + idiomas);
        System.out.println("**********   Numero de descargas: " + numeroDeDescargas);
        System.out.println("-----------------------------------------------------------------------\n");
    }

    public void mostrarAutores() {
        System.out.println("**********   Autor: " + autor + " " + "-------->" + " Titulo del Libro: " + titulo);
    }

    // Método para convertir códigos de idioma a nombres completos
    private String convertirIdiomas(String idiomas) {
        String[] codigos = idiomas.split(",\\s*");
        StringBuilder nombresIdiomas = new StringBuilder();

        for (String codigo : codigos) {
            String nombre = NOMBRES_IDIOMAS.getOrDefault(codigo, codigo); // Si no está en el mapeo, devuelve el código
            if (nombresIdiomas.length() > 0) {
                nombresIdiomas.append(", ");
            }
            nombresIdiomas.append(nombre);
        }
        return nombresIdiomas.toString();
    }

    public void listarLibrosPorIdioma() {
        String idiomasConvertidos = convertirIdiomas(idiomas);
        System.out.println("**** Titulo: " + titulo + "\n" + "**** Idioma: " + idiomasConvertidos);
    }
}





