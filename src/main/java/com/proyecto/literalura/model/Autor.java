package com.proyecto.literalura.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "autores")

public class Autor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String nombre;
    private Integer añoNacimiento;
    private Integer añoFallecimiento;
    @OneToMany(mappedBy = "autor", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Libro> libros;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
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

    public List<Libro> getLibros() {
        return libros;
    }

    public void setLibros(List<Libro> libros) {
        this.libros = libros;
    }
    public Autor(){}

    public Autor(List<DatosAutor> datosAutors) {
        this.nombre = datosAutors.get(0).nombre();
        this.añoNacimiento = datosAutors.get(0).añoNacimiento();
        this.añoFallecimiento = datosAutors.get(0).añoFallecimiento();
    }

    @Override
    public String toString() {
        return "* * * * Autor * * * *" + "\n"+
                "Nombre='" + nombre + "\n"+
                "Año de Fallecimiento=" + añoFallecimiento + "\n"+
                "Año de Nacimiento=" + añoNacimiento + "\n"+
                "Libros = " + libros.stream().map(Libro::getTitulo).toList() + "\n";
    }
}
