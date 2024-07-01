package com.codigo.mscastrejoncarrascohexagonal.domain.exceptions.advice;

import com.codigo.mscastrejoncarrascohexagonal.domain.aggregates.constants.Constantes;
import com.codigo.mscastrejoncarrascohexagonal.domain.exceptions.personalizada.PersonaException;
import com.codigo.mscastrejoncarrascohexagonal.domain.response.ResponseBase;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.io.IOException;
import java.util.Optional;

@ControllerAdvice
public class GlobalException {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseBase> handleException(Exception ex){
        ResponseBase response = new ResponseBase(Constantes.CODIGO_ERROR_EXCEPTION, "ERROR INTERNO DEL SERVIDOR "+ex.getMessage(), Optional.empty());
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<ResponseBase> handleNullPointer(Exception ex){
        ResponseBase response = new ResponseBase(Constantes.CODIGO_ERROR_NULLPOINTER, "HAY UN DATO NULO "+ex.getMessage(), Optional.empty());
        return new ResponseEntity<>(response, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(IOException.class)
    public ResponseEntity<ResponseBase> handleIOException(Exception ex){
        ResponseBase response = new ResponseBase(Constantes.CODIGO_ERROR_IOEXCEPTION, "HAY UN DATO NULO "+ex.getMessage(), Optional.empty());
        return new ResponseEntity<>(response, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ResponseBase> handleRuntimeException(Exception ex){
        ResponseBase response = new ResponseBase(Constantes.CODIGO_ERROR_RUNTIMEN, "HAY UN DATO NULO "+ex.getMessage(), Optional.empty());
        return new ResponseEntity<>(response, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(PersonaException.class)
    public ResponseEntity<ResponseBase> handlePersonaException(Exception ex){
        ResponseBase response = new ResponseBase(Constantes.CODIGO_ERROR_PERSONALIZADA, "ERROR EN LA PERSONA "+ex.getMessage(), Optional.empty());
        return new ResponseEntity<>(response, HttpStatus.CONFLICT);
    }
}
