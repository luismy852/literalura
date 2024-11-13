package com.proyecto.literalura.model;

import com.fasterxml.jackson.annotation.JsonAlias;

public record DatosAutor(
        @JsonAlias("name") String nombre,
        @JsonAlias("birth_year") Integer añoNacimiento,
        @JsonAlias("death_year") Integer añoFallecimiento
) {
}
