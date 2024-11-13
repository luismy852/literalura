package com.proyecto.literalura.principal;

import com.proyecto.literalura.model.*;
import com.proyecto.literalura.repository.AutorRepository;
import com.proyecto.literalura.repository.LibroRepository;
import com.proyecto.literalura.service.ConsumoApi;
import com.proyecto.literalura.service.ConvierteDatos;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Scanner;

public class Principal {

    @Autowired
    private LibroRepository repository;
    @Autowired
    private AutorRepository autorRepository;

    public Principal(LibroRepository repository, AutorRepository autorRepository) {
        this.repository = repository;
        this.autorRepository = autorRepository;
    }



    private final String URL_BASE = "https://gutendex.com/books/?search=";
    private Scanner teclado = new Scanner(System.in);
    private ConsumoApi consumoApi = new ConsumoApi();
    private ConvierteDatos convierteDatos = new ConvierteDatos();
    private String busqueda;


    public void menu(){

        System.out.println("Bienvenido");
        int decision = -1;

        while (decision != 0){
            System.out.println("""
                            Elige la opción a través de su número:
                            
                            1. Buscar libros
                            2. Listar libros consultados
                            3. Listar Autores consultados
                            4. Listar autores vivos en un determinado años
                            5. Listar libro por idioma
                            0. salir
                            """);
            decision = teclado.nextInt();
            teclado.nextLine();

            switch (decision){
                case 1:
                    System.out.println("Escribe el nombre del libro que deseas buscar");
                    busqueda = teclado.nextLine();

                    var json = consumoApi.obtenerDatos(URL_BASE + busqueda.replace(" ", "%20"));
                    System.out.println(busqueda);
                    var datos =  convierteDatos.obtenerDatos(json, Datos.class);
                    List<DatosAutor> datosAutorList = datos.resultados().get(0).datosAutores();
                    List<DatosLibro> datosLibroList = datos.resultados();
                    System.out.println(json);

                    Autor autor = new Autor(datosAutorList);
                    if (!autorRepository.existsByNombre(autor.getNombre())){
                        autorRepository.save(autor);
                    } else {
                        var nombre = autor.getNombre();
                        autor = autorRepository.findByNombreIgnoreCase(nombre);
                        System.out.println(autor);
                        if (repository.existsByTitulo(datosLibroList.get(0).titulo())){
                            System.out.println(datosLibroList.get(0).titulo());
                            System.out.println("El libro ya está registrado :)");
                            break;
                        }
                    }

                    Libro libro = new Libro(datosLibroList,autor);
                    repository.save(libro);
                    System.out.println(libro);
                    break;

                case 2:
                    System.out.println(repository.findAll());
                    break;

                case 3:
                    System.out.println(autorRepository.findAll());
                    break;

                case 4:
                    System.out.println("ingresa el año");
                    var añoSeleccionado = teclado.nextInt();
                    System.out.println(autorRepository.findByAñoFallecimientoGreaterThanAndAñoNacimientoIsLessThan(
                            añoSeleccionado, añoSeleccionado));
                    break;


                case 5:
                    System.out.println("""
                            Escribe las siglas del idioma que deseas buscar
                            
                            es Español
                            en Inglés
                            fr Francés
                            pt Portugés
                            """);
                    var busquedaIdioma = teclado.nextLine();
                    var resultadoBusqueda =repository.findByLenguajeContains(busquedaIdioma);
                    if (resultadoBusqueda.isEmpty()){
                        System.out.println("No se encontraron libros en el idioma seleccionado");
                    }else {
                        System.out.println(resultadoBusqueda);
                    }
                    break;

                case 0:
                    System.out.println("Gracias por utilizar el programa");

                default:
                    System.out.println("Escoge una opción valida");
            }

        }

    }
}
