package br.com.lima.exceptions;

import java.io.Serial;

public class EntityNotFoundException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = -3624977767430721733L;

    private static final String MESSAGE = "Entidade do tipo %s com o id: %d não encontrada.";

    public EntityNotFoundException(String classe, Long id) {
        super(String.format(MESSAGE, classe, id));
    }

}
