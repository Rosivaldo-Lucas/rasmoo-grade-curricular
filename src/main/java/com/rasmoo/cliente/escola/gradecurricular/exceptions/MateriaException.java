package com.rasmoo.cliente.escola.gradecurricular.exceptions;

import org.springframework.http.HttpStatus;

public class MateriaException extends EntityException {

    public MateriaException(final String mensagem, final HttpStatus httpStatus) {
        super(mensagem, httpStatus);
    }

}
