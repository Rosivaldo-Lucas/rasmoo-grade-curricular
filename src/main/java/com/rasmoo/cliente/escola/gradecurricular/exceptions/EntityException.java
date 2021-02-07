package com.rasmoo.cliente.escola.gradecurricular.exceptions;

import org.springframework.http.HttpStatus;

public abstract class EntityException extends RuntimeException {

    private final HttpStatus httpStatus;

    public EntityException(final String mensagem, final HttpStatus httpStatus) {
        super(mensagem);
        this.httpStatus = httpStatus;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

}
