package com.rasmoo.cliente.escola.gradecurricular.hadlers;

import com.rasmoo.cliente.escola.gradecurricular.exceptions.MateriaException;
import com.rasmoo.cliente.escola.gradecurricular.model.ErrorMapResponse;
import com.rasmoo.cliente.escola.gradecurricular.model.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ResourceHandler {

    @ExceptionHandler({ MethodArgumentNotValidException.class })
    public ResponseEntity<ErrorMapResponse> handlerMethodArgumentNotValidException(final MethodArgumentNotValidException methodArgumentNotValidException) {
        final Map<String, String> erros = new HashMap<>();

        methodArgumentNotValidException.getBindingResult().getAllErrors().forEach((error) -> {
            final String campo = ((FieldError)error).getField();
            final String mensagem = error.getDefaultMessage();

            erros.put(campo, mensagem);
        });

        final ErrorMapResponse errorMapResponse = new ErrorMapResponse();
        errorMapResponse.setHttpStatus(HttpStatus.BAD_REQUEST.value());
        errorMapResponse.setErros(erros);
        errorMapResponse.setTimestamp(System.currentTimeMillis());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMapResponse);
    }

    @ExceptionHandler({ MateriaException.class })
    public ResponseEntity<ErrorResponse> handlerMateriaException(final MateriaException materiaException) {
        final ErrorResponse error = new ErrorResponse();
        error.setHttpStatus(materiaException.getHttpStatus().value());
        error.setMensagem(materiaException.getMessage());
        error.setTimestamp(System.currentTimeMillis());

        return ResponseEntity.status(materiaException.getHttpStatus()).body(error);
    }

}
