package com.proyecto.literalura.principal;

import com.proyecto.literalura.model.*;
import com.proyecto.literalura.repository.LibroRepository;
import com.proyecto.literalura.service.ConsumoApi;
import com.proyecto.literalura.service.ConvierteDatos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Principal {

    @Autowired
    private LibroRepository repository;

    public Principal(LibroRepository repository) {
        this.repository = repository;
    }

    private final String URL_BASE = "https://gutendex.com/books/?search=";
    private Scanner teclado = new Scanner(System.in);
    private ConsumoApi consumoApi = new ConsumoApi();
    private ConvierteDatos convierteDatos = new ConvierteDatos();
    private String busqueda;


    public void menu(){


        Integer decision = -1;

        while (decision != 0){
            System.out.println("""
                            Elige la opción a traves de su numero:
                            
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
                    List<DatosLibro> datosLibroList = datos.resultados();
                    System.out.println(json);
                    System.out.println(datos);
                    Libro libro = new Libro(datosLibroList);
                    System.out.println(libro);
                    try {
                        if (repository.existsByTitulo(libro.getTitulo())){
                            System.out.println("libro ya esta regitrado :)");
                        }else {

                            repository.save(libro);
                        }

                    } catch (DataIntegrityViolationException e){
                        System.out.println("Este libro ya esta registraso :)");
                    }
                    break;

                case 2:
                    System.out.println(repository.findAll());
                    break;

                case 3:
                    var libros = repository.findAll();
                    var autores = libros.stream().map(l-> new Autor(l.getAutorNombre(), l.getAñoNacimiento(), l.getAñoFallecimiento(), Collections.singletonList(l.getTitulo())))
                            .toList();

                    break;

                case 4:
                    System.out.println("ingresa el año");
                    var añoSeleccionado = teclado.nextInt();
                     var autoresVivos = repository.findByAñoFallecimientoGreaterThan(añoSeleccionado);
                     autoresVivos.stream().map(l-> new Autor(l.getAutorNombre(), l.getAñoNacimiento(), l.getAñoFallecimiento(), Collections.singletonList(l.getTitulo())))
                             .forEach(System.out::println);
                    break;


                case 5:
                    System.out.println("""
                            Escribe las siglas del idoma que deseas buscar
                            
                            es Español
                            en Ingles
                            fr Frances
                            pt Portuges
                            """);
                    var busquedaIdioma = teclado.nextLine();
                    var resultadoBusqueda =repository.findByLenguajeContains(busquedaIdioma);
                    if (resultadoBusqueda.isEmpty()){
                        System.out.println("No se encontraron libros en el idioma seleccionado");
                    }else {
                        System.out.println(resultadoBusqueda);
                    }
                    break;
            }

        }

    }
}
