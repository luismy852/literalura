package com.proyecto.literalura.model;

import jakarta.persistence.*;

import java.util.List;
@Entity
@Table(name = "libros")

public class Libro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String titulo;
    @ManyToOne
    private Autor autor;
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

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
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

    public Libro(List<DatosLibro> datosLibroList, Autor autor) {
        this.titulo = datosLibroList.get(0).titulo();
        this.autor = autor;
        this.lenguaje = String.valueOf(datosLibroList.get(0).datosLenguajes());
        this.numeroDescargas = datosLibroList.get(0).numeroDescargas();
    }

    @Override
    public String toString() {
        return "* * * * Libro * * * *" + "\n"+
                "Titulo " + titulo + '\n' +
                "Autor " + autor.getNombre() + "\n"+
                "Lenguaje " + lenguaje + "\n"+
                "Numero de Descargas=" + numeroDescargas + "\n"
                ;
    }
}
