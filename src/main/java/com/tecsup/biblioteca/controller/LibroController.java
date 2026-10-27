package com.tecsup.biblioteca.controller;

import com.tecsup.biblioteca.model.Libro;
import com.tecsup.biblioteca.service.LibroService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/libros")
public class LibroController {

    private final LibroService libroService;

    public LibroController(LibroService libroService) {
        this.libroService = libroService;
    }

    @PostMapping                                  // POST /api/libros → registrar
    public ResponseEntity<Libro> registrar(@RequestBody Libro libro) {
        return ResponseEntity.status(HttpStatus.CREATED).body(libroService.registrar(libro));
    }

    @GetMapping                                   // GET /api/libros → listar
    public List<Libro> listar() {
        return libroService.listar();
    }

    @GetMapping("/{id}")                          // GET /api/libros/1 → buscar por id
    public Libro buscarPorId(@PathVariable Long id) {
        return libroService.buscarPorId(id);
    }

    @DeleteMapping("/{id}")                       // DELETE /api/libros/1 → eliminar
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        libroService.eliminar(id);
        return ResponseEntity.noContent().build();   // 204: eliminado sin contenido
    }

    // Reto adicional
    @GetMapping("/buscar")                        // GET /api/libros/buscar?titulo=...
    public List<Libro> buscarPorTitulo(@RequestParam String titulo) {
        return libroService.buscarPorTitulo(titulo);
    }

    @GetMapping("/anio-mayor")                    // GET /api/libros/anio-mayor?anio=2000
    public List<Libro> buscarPorAnioMayor(@RequestParam Integer anio) {
        return libroService.buscarPorAnioMayor(anio);
    }
}