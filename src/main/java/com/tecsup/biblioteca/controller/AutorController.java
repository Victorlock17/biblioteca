package com.tecsup.biblioteca.controller;

import com.tecsup.biblioteca.model.Autor;
import com.tecsup.biblioteca.service.AutorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController                  // Esta clase responde peticiones HTTP con JSON
@RequestMapping("/api/autores")  // Todas las URLs empiezan con /api/autores
public class AutorController {

    private final AutorService autorService;

    public AutorController(AutorService autorService) {
        this.autorService = autorService;
    }

    @PostMapping                 // POST /api/autores  → registrar
    public ResponseEntity<Autor> registrar(@RequestBody Autor autor) {
        return ResponseEntity.status(HttpStatus.CREATED).body(autorService.registrar(autor));
    }

    @GetMapping                  // GET /api/autores  → listar
    public List<Autor> listar() {
        return autorService.listar();
    }
}