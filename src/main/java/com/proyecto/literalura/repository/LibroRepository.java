package com.proyecto.literalura.repository;


import com.proyecto.literalura.model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LibroRepository extends JpaRepository<Libro, Long> {

    public Libro findByTituloIgnoreCase(String tituloLibro);

    public boolean existsByTitulo(String titulo);

    public List<Libro> findByLenguajeContains(String lengueje);
}
