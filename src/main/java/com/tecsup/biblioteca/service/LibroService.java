package com.tecsup.biblioteca.service;

import com.tecsup.biblioteca.exception.RecursoNoEncontradoException;
import com.tecsup.biblioteca.exception.ReglaNegocioException;
import com.tecsup.biblioteca.model.Autor;
import com.tecsup.biblioteca.model.Libro;
import com.tecsup.biblioteca.repository.AutorRepository;
import com.tecsup.biblioteca.repository.LibroRepository;
import org.springframework.stereotype.Service;
import java.time.Year;
import java.util.List;

@Service
public class LibroService {

    private final LibroRepository libroRepository;
    private final AutorRepository autorRepository;

    public LibroService(LibroRepository libroRepository, AutorRepository autorRepository) {
        this.libroRepository = libroRepository;
        this.autorRepository = autorRepository;
    }

    public Libro registrar(Libro libro) {
        validar(libro);

        // Regla: el libro debe pertenecer a un autor existente
        if (libro.getAutor() == null || libro.getAutor().getId() == null) {
            throw new ReglaNegocioException("El libro debe indicar un autor");
        }
        Long autorId = libro.getAutor().getId();
        Autor autor = autorRepository.findById(autorId)
                .orElseThrow(() -> new RecursoNoEncontradoException("Autor no encontrado con id " + autorId));

        libro.setAutor(autor);
        return libroRepository.save(libro);
    }

    private void validar(Libro libro) {
        // Regla 3: título obligatorio
        if (libro.getTitulo() == null || libro.getTitulo().isBlank()) {
            throw new ReglaNegocioException("El título es obligatorio");
        }
        // Regla 4: editorial no vacía
        if (libro.getEditorial() == null || libro.getEditorial().isBlank()) {
            throw new ReglaNegocioException("La editorial no puede estar vacía");
        }
        // Regla 5: año obligatorio y no mayor al año actual
        if (libro.getAnioPublicacion() == null) {
            throw new ReglaNegocioException("El año de publicación es obligatorio");
        }
        int anioActual = Year.now().getValue();
        if (libro.getAnioPublicacion() > anioActual) {
            throw new ReglaNegocioException("El año no puede ser mayor a " + anioActual);
        }
    }

    public List<Libro> listar() {
        return libroRepository.findAll();
    }

    public Libro buscarPorId(Long id) {
        return libroRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException("Libro no encontrado con id " + id));
    }

    public void eliminar(Long id) {
        if (!libroRepository.existsById(id)) {
            throw new RecursoNoEncontradoException("Libro no encontrado con id " + id);
        }
        libroRepository.deleteById(id);
    }

    // Reto adicional
    public List<Libro> buscarPorTitulo(String titulo) {
        return libroRepository.findByTitulo(titulo);
    }

    public List<Libro> buscarPorAnioMayor(Integer anio) {
        return libroRepository.findByAnioPublicacionGreaterThan(anio);
    }
}