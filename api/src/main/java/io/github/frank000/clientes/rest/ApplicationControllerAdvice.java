package io.github.frank000.clientes.rest;

import io.github.frank000.clientes.rest.exception.ApiErrors;
import io.github.frank000.clientes.rest.exception.CustomException;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ApplicationControllerAdvice {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiErrors handleValidationErrors(MethodArgumentNotValidException ex){
        BindingResult bindingResult = ex.getBindingResult();
        List<String> messages = bindingResult
                                .getAllErrors()
                                .stream()
                                .map(objectError -> objectError.getDefaultMessage())
                                .collect(Collectors.toList());

        return new ApiErrors(messages);
    }

    @ExceptionHandler(CustomException.class)
    public ApiErrors handleValidationErrors(CustomException ex){
        String messages = ex.getMessage();

        return new ApiErrors(messages);
    }

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity handleResponseStatusErrors(ResponseStatusException ex){
        String message = ex.getReason();
        HttpStatus status = ex.getStatus();
        ApiErrors apiErrors = new ApiErrors(message);

        return new ResponseEntity(apiErrors, status);
    }


}

