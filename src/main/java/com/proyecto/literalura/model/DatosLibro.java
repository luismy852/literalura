package com.proyecto.literalura.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)

public record DatosLibro(
        @JsonAlias("title") String titulo,
        @JsonAlias("authors")List<DatosAutor> datosAutores,
        @JsonAlias("languages") List<String> datosLenguajes,
        @JsonAlias("download_count") Integer numeroDescargas
        ) {
}
