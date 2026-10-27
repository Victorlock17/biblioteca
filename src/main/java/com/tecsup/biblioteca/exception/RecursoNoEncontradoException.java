package com.tecsup.biblioteca.exception;

// Se lanza cuando se busca un libro o autor que no existe
public class RecursoNoEncontradoException extends RuntimeException {
    public RecursoNoEncontradoException(String mensaje) {
        super(mensaje);
    }
}