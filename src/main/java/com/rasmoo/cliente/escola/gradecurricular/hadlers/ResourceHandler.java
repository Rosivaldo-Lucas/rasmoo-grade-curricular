package com.rasmoo.cliente.escola.gradecurricular.hadlers;

import com.rasmoo.cliente.escola.gradecurricular.exceptions.MateriaException;
import com.rasmoo.cliente.escola.gradecurricular.model.ErrorResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ResourceHandler {

    @ExceptionHandler({ MateriaException.class })
    public ResponseEntity<ErrorResponse> handlerMateriaException(final MateriaException materiaException) {
        final ErrorResponse error = new ErrorResponse();
        error.setHttpStatus(materiaException.getHttpStatus().value());
        error.setMensagem(materiaException.getMessage());
        error.setTimestamp(System.currentTimeMillis());

        return ResponseEntity.status(materiaException.getHttpStatus()).body(error);
    }

}
