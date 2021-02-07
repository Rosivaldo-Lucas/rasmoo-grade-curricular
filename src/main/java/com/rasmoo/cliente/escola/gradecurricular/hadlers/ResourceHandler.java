package com.rasmoo.cliente.escola.gradecurricular.hadlers;

import com.rasmoo.cliente.escola.gradecurricular.exceptions.CursoException;
import com.rasmoo.cliente.escola.gradecurricular.exceptions.EntityException;
import com.rasmoo.cliente.escola.gradecurricular.exceptions.MateriaException;
import com.rasmoo.cliente.escola.gradecurricular.model.Response;
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
    public ResponseEntity<Response<Map<String, String>>> handlerMethodArgumentNotValidException(final MethodArgumentNotValidException methodArgumentNotValidException) {
        final Map<String, String> erros = new HashMap<>();

        final Response<Map<String, String>> response = new Response<>();

        methodArgumentNotValidException.getBindingResult().getAllErrors().forEach((error) -> {
            final String campo = ((FieldError)error).getField();
            final String mensagem = error.getDefaultMessage();

            erros.put(campo, mensagem);
        });

        response.setStatusCode(HttpStatus.BAD_REQUEST.value());
        response.setData(erros);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler({ MateriaException.class, CursoException.class })
    public ResponseEntity<Response<String>> handlerEntityException(final EntityException entityException) {
        final Response<String> response = new Response<>();

        response.setStatusCode(entityException.getHttpStatus().value());
        response.setData(entityException.getMessage());

        return ResponseEntity.status(entityException.getHttpStatus()).body(response);
    }

}
