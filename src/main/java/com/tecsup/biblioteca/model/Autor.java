package com.tecsup.biblioteca.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity                                              // Esta clase será una tabla
@Table(name = "autor")
public class Autor {

    @Id                                              // Clave primaria
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // MySQL asigna el id solo
    private Long id;

    private String nombre;
    private String nacionalidad;

    // Un autor tiene muchos libros. mappedBy indica que la columna está en Libro.
    // @JsonIgnore evita un bucle al convertir a JSON.
    @OneToMany(mappedBy = "autor")
    @JsonIgnore
    private List<Libro> libros = new ArrayList<>();

    public Autor() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getNacionalidad() { return nacionalidad; }
    public void setNacionalidad(String nacionalidad) { this.nacionalidad = nacionalidad; }
    public List<Libro> getLibros() { return libros; }
    public void setLibros(List<Libro> libros) { this.libros = libros; }
}