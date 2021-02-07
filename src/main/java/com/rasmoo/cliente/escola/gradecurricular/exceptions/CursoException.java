package com.rasmoo.cliente.escola.gradecurricular.exceptions;

import org.springframework.http.HttpStatus;

public class CursoException extends EntityException {

    public CursoException(final String mensagem, final HttpStatus httpStatus) {
        super(mensagem, httpStatus);
    }

}
