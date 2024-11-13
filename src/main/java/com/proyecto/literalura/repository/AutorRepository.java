package com.proyecto.literalura.repository;

import com.proyecto.literalura.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AutorRepository extends JpaRepository<Autor, Long> {
    public boolean existsByNombre(String nombre);

    public Autor findByNombreIgnoreCase(String nombreBuscado);

    public List<Autor> findByAñoFallecimientoGreaterThanAndAñoNacimientoIsLessThan(Integer año, Integer año2);
}
