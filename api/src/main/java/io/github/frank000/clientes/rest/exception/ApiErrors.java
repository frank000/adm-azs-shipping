package io.github.frank000.clientes.rest.exception;

import lombok.Getter;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.Arrays;
import java.util.List;

public class ApiErrors {

    @Getter
    private List<String> errors;

    public ApiErrors(List<String> list){
        this.errors =list;
    }

    public ApiErrors(String message){
        this.errors = Arrays.asList(message);
    }
}
