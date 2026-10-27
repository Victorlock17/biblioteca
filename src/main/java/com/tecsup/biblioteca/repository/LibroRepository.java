package com.tecsup.biblioteca.repository;

import com.tecsup.biblioteca.model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface LibroRepository extends JpaRepository<Libro, Long> {

    // Reto adicional: Spring crea la consulta solo con el nombre del método
    List<Libro> findByTitulo(String titulo);

    List<Libro> findByAnioPublicacionGreaterThan(Integer anio);
}