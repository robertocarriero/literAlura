package com.robertocarriero.literAlura.principal;

import com.robertocarriero.literAlura.controller.LibrosController;
import com.robertocarriero.literAlura.model.Datos;
import com.robertocarriero.literAlura.model.DatosAutor;
import com.robertocarriero.literAlura.model.DatosLibros;
import com.robertocarriero.literAlura.model.Libros;
import com.robertocarriero.literAlura.repositry.LibrosRepository;
import com.robertocarriero.literAlura.service.ConsumirApi;
import com.robertocarriero.literAlura.service.ConvierteDatos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;

import java.util.*;


public class Principal {

    private Scanner teclado = new Scanner(System.in);
    public static final String URL_BASE = "https://gutendex.com/books/";
    private ConsumirApi consumirApi = new ConsumirApi();
    private ConvierteDatos conversor = new ConvierteDatos();
    private LibrosRepository repositorio;
    private List<Libros> librosList;

    public Principal(LibrosRepository repository) {
        this.repositorio = repository;
    }

    @Autowired
    private LibrosController librosController;

    public void muestraElMenu() {
        var json = ConsumirApi.obtenerDatos(URL_BASE);
        //System.out.println(json);
        var datos = conversor.obtenerDatos(json, Datos.class);
        //System.out.println(datos);

        var opcion = -1;
        while (opcion != 0) {
            System.out.println("*****************************************************************\n");
            var menu = """
                    1 - Buscar Libro por Título
                    2 - Mostrar Listado de Libros Registrados
                    3 - Mostrar Listado de Autores Registrados 
                    4 - Listar Autores Vivos en un Determinado Año
                    5 - Listar Libros por Idioma
                    6 - Buscar Libros por Idioma
                    7 - Buscar los 15 Libros mas buscados
                    
                    
                    
                    0 - Salir
                    """;
            System.out.println(menu);
            opcion = teclado.nextInt();
            teclado.nextLine();

            switch (opcion) {
                case 1:
                    buscarLibroPorNombre();
                    break;
                case 2:
                    listarLibrosGuardados();
                    break;
                case 3:
                    listarAutoresGuardados();
                    break;
                case 4:
                    listarAutoresVivosSegunFecha();
                    break;
                case 5:
                    listarLibrosPorIdioma();
                    break;
                case 6:
                    buscarLibroPorIdioma();
                    break;
                case 7:
                    mostrarLibrosMasBuscados();
                    break;
                case 0:

                    System.out.println("Saliendo del programa...");
                    break;
                default:
                    System.out.println("Opción inválida. Por favor, intente nuevamente.");
                    break;
            }
        }
    }
    private void buscarLibroPorNombre() {
        try {
            System.out.print("Ingrese el nombre del libro que está buscando: ");
            var tituloLibro = teclado.nextLine();

            // Consumir la API
            var json = consumirApi.obtenerDatos(URL_BASE + "?search=" + tituloLibro.replace(" ", "+"));

            // Convertir los datos de la API
            var busquedaDeDatos = conversor.obtenerDatos(json, Datos.class);

            // Buscar el libro por título
            Optional<DatosLibros> librosBuscados = busquedaDeDatos.resultado().stream()
                    .filter(l -> l.titulo().toUpperCase().contains(tituloLibro.toUpperCase()))
                    .findFirst();

            if (librosBuscados.isPresent()) {
                System.out.println("Libro encontrado:");
                Libros libroConvertido = ConvierteDatos.convertirADatosLibros(librosBuscados.get());

                // Guardar en la base de datos
                repositorio.save(libroConvertido);

                // Mostrar el libro
                libroConvertido.imprimirEnFormato();
            } else {
                System.out.println("Libro no disponible o no encontrado.");
            }
        } catch (Exception e) {
            // Manejo de errores
            System.out.println("Ocurrió un error al buscar el libro. Por favor, intente nuevamente.");
            e.printStackTrace();
        }
    }

    private void listarLibrosGuardados() {
            // Obtenemos todos los libros guardados en la base de datos
            List<Libros> librosGuardados = repositorio.findAll();

            // Verificamos si hay libros en la base de datos
            if (librosGuardados.isEmpty()) {
                System.out.println("No hay libros guardados en la base de datos.");
                    return;
        }
            // Iteramos y mostramos cada libro usando el método mostrarLibros
            librosGuardados.forEach(Libros::mostrarLibros);
    }

    private void listarAutoresGuardados() {
        // Obtenemos todos los libros guardados en la base de datos
            List<Libros> librosGuardados = repositorio.findAll();

        // Verificamos si hay libros en la base de datos
            if (librosGuardados.isEmpty()) {
                System.out.println("No hay Autores guardados en la base de datos.");
                    return;
        }
        // Iteramos y mostramos cada libro usando el método mostrarAutores
            librosGuardados.forEach(Libros::mostrarAutores);
    }

    private void listarLibrosPorIdioma() {
            // Obtenemos todos los libros guardados en la base de datos
            List<Libros> librosGuardados = repositorio.findAll();

            // Verificamos si hay libros en la base de datos
            if (librosGuardados.isEmpty()) {
                System.out.println("No hay Libros guardados en la base de datos.");
                return;
        }
            librosGuardados.forEach(libro -> {
                if (libro != null) {
                    libro.listarLibrosPorIdioma();
                } else {
                    System.out.println("Se encontró un libro nulo en la base de datos.");
                }
            });
        }

    private void  buscarLibroPorIdioma() {
        try {
            System.out.print("Ingrese el código del idioma\n\n" +
                    "Español = es\n" +
                    "Italiano= it\n" +
                    "Inglés = en\n" +
                    "Portugues = pt\n" +
                    "Francés = fr\n" +
                    "Tagalo= tl \n\n" +
                    " \n");
            String idioma = teclado.nextLine();

            if (idioma.isBlank() || idioma.length() != 2) {
                System.out.println("El idioma ingresado no es válido. Intente nuevamente con un código de dos letras (ejemplo: 'en').");
                return;
            }

            // Obtener todos los libros guardados
            List<Libros> librosGuardados = repositorio.findAll();

            // Filtrar los libros por idioma
            List<Libros> librosEnIdioma = librosGuardados.stream()
                    .filter(libro -> libro.getIdiomas() != null && libro.getIdiomas().contains(idioma))
                    .toList();

            // Verificar si hay resultados
            if (librosEnIdioma.isEmpty()) {
                System.out.println("No se encontraron libros en el idioma especificado (" + idioma + ").");
                return;
            }

            // Mostrar los libros encontrados
            System.out.println("Libros encontrados en el idioma '" + idioma + "':");
            librosEnIdioma.forEach(libro -> {
                System.out.println("Título: " + libro.getTitulo());
                System.out.println("Autor: " + libro.getAutor());
                System.out.println("Idiomas: " + libro.getIdiomas());
                System.out.println("-------------------------------------------------");
            });

        } catch (Exception e) {
            System.out.println("Ocurrió un error al buscar los libros por idioma. Por favor, intente nuevamente.");
            e.printStackTrace();
        }
    }


    private void listarAutoresVivosSegunFecha() {
        System.out.println("Ingresar año para verificar autores vivos en esa fecha:");
        int anio = teclado.nextInt();
        teclado.nextLine(); // Consumir la línea extra

        // Obtenemos todos los libros guardados en la base de datos
        List<Libros> librosGuardados = repositorio.findAll();

        if (librosGuardados.isEmpty()) {
            System.out.println("No hay autores registrados en la base de datos.");
            return;
        }

        // Filtramos autores vivos en el año dado
        List<Libros> autoresVivos = librosGuardados.stream()
                .filter(libro -> libro.getFechaFallecimiento() == null || libro.getFechaFallecimiento() <= anio)
                .toList();

        if (autoresVivos.isEmpty()) {
            System.out.println("No se encontraron autores vivos en el año " + anio + ".");
        } else {
            System.out.println("Autores vivos en el año " + anio + ":");
            autoresVivos.forEach(libro -> System.out.println("Autor: " + libro.getAutor() + " - Título: " + libro.getTitulo()));
        }
    }

    private void mostrarLibrosMasBuscados() {
            System.out.println("Obteniendo los 15 libros más buscados...");
            var json = ConsumirApi.obtenerDatos(URL_BASE);
            var datos = conversor.obtenerDatos(json, Datos.class);

            datos.resultado().stream()
                    .sorted(Comparator.comparing(DatosLibros::numeroDeDescargas).reversed())
                    .limit(15)
                    .map(l -> l.titulo().toUpperCase())
                    .forEach(System.out::println);
    }
}































