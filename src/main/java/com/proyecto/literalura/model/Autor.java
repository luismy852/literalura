package com.proyecto.literalura.model;

import java.util.List;

public class Autor {
    private String nombre;
    private Integer añoNacimiento;
    private Integer añoFallecimiento;
    private List<String> libros;

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

    public List<String> getLibros() {
        return libros;
    }

    public void setLibros(List<String> libros) {
        this.libros = libros;
    }

    public Autor(String nombre, Integer añoNacimiento, Integer añoFallecimiento, List<String> libros) {
        this.nombre = nombre;
        this.añoNacimiento = añoNacimiento;
        this.añoFallecimiento = añoFallecimiento;
        this.libros = libros;
    }

    @Override
    public String toString() {
        return "* * * *" + "\n" +
                "nombre " + nombre + '\n' +
                "año de nacimiento =" + añoNacimiento + "\n" +
                "año de fallecimiento =" + añoFallecimiento + "\n" +
                "libros =" + libros;
    }
}
