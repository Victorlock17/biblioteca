package com.tecsup.biblioteca.service;

import com.tecsup.biblioteca.exception.ReglaNegocioException;
import com.tecsup.biblioteca.model.Autor;
import com.tecsup.biblioteca.repository.AutorRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AutorService {

    private final AutorRepository autorRepository;

    // Spring entrega el repositorio automáticamente
    public AutorService(AutorRepository autorRepository) {
        this.autorRepository = autorRepository;
    }

    public Autor registrar(Autor autor) {
        // Regla 1: nombre obligatorio
        if (autor.getNombre() == null || autor.getNombre().isBlank()) {
            throw new ReglaNegocioException("El nombre del autor es obligatorio");
        }
        // Regla 2: nacionalidad obligatoria
        if (autor.getNacionalidad() == null || autor.getNacionalidad().isBlank()) {
            throw new ReglaNegocioException("La nacionalidad del autor es obligatoria");
        }
        return autorRepository.save(autor);   // INSERT automático
    }

    public List<Autor> listar() {
        return autorRepository.findAll();     // SELECT automático
    }
}