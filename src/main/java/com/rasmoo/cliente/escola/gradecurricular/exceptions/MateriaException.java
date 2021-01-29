package com.rasmoo.cliente.escola.gradecurricular.exceptions;

import org.springframework.http.HttpStatus;

public class MateriaException extends RuntimeException {

    private final HttpStatus httpStatus;

    public MateriaException(final String mensagem, final HttpStatus httpStatus) {
        super(mensagem);
        this.httpStatus = httpStatus;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}
