package com.tecsup.biblioteca.repository;

import com.tecsup.biblioteca.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;

// JpaRepository ya trae save, findAll, findById, existsById y deleteById.
// No escribimos SQL: Spring lo genera.
public interface AutorRepository extends JpaRepository<Autor, Long> {
}