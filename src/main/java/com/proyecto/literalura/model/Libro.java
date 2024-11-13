package com.proyecto.literalura.model;

import jakarta.persistence.*;

import java.util.Collections;
import java.util.List;
@Entity
@Table(name = "libros")

public class Libro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String titulo;
    private String autorNombre;
    private Integer añoNacimiento;
    private Integer añoFallecimiento;
    private String lenguaje;
    private Integer numeroDescargas;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutorNombre() {
        return autorNombre;
    }

    public void setAutorNombre(String autorNombre) {
        this.autorNombre = autorNombre;
    }

    public Integer getAñoNacimiento() {
        return añoNacimiento;
    }

    public void setAñoNacimiento(Integer añoNacimiento) {
        this.añoNacimiento = añoNacimiento;
    }

    public Integer getAñoFallecimiento() {
        return añoFallecimiento;
    }

    public void setAñoFallecimiento(Integer añoFallecimiento) {
        this.añoFallecimiento = añoFallecimiento;
    }

    public String getLenguaje() {
        return lenguaje;
    }

    public void setLenguaje(String lenguaje) {
        this.lenguaje = lenguaje;
    }

    public Integer getNumeroDescargas() {
        return numeroDescargas;
    }

    public void setNumeroDescargas(Integer numeroDescargas) {
        this.numeroDescargas = numeroDescargas;
    }

    public Libro(){}

    public Libro(List<DatosLibro> datos) {
        this.titulo = datos.get(0).titulo();
        this.autorNombre = datos.get(0).datosAutores().get(0).nombre();
        this.añoNacimiento = datos.get(0).datosAutores().get(0).añoNacimiento();
        this.añoFallecimiento = datos.get(0).datosAutores().get(0).añoFallecimiento();
        this.lenguaje = String.valueOf(datos.get(0).datosLenguajes());
        this.numeroDescargas = datos.get(0).numeroDescargas();
    }

    @Override
    public String toString() {
        return "********************** " + "\n"+
                "Libro" +"\n"+
                "Titulo='" + titulo + '\n' +
                "Autor='" + autorNombre + '\n' +
                ", año de nacimiento='" + añoNacimiento + '\n' +
                ", año de Fallecimiento='" + añoFallecimiento + '\n' +
                ", lenguaje='" + lenguaje + '\n' +
                ", numero de descargas=" + numeroDescargas + "\n" +
                '\n';
    }
}
