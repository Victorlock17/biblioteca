package com.tecsup.biblioteca.exception;

// Se lanza cuando una regla de negocio no se cumple (por ejemplo, título vacío)
public class ReglaNegocioException extends RuntimeException {
    public ReglaNegocioException(String mensaje) {
        super(mensaje);
    }
}