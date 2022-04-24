package io.github.frank000.clientes.rest;

import io.github.frank000.clientes.model.entity.Cliente;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;

@RestController
@RequestMapping("/")
public class ApiController {

    @GetMapping()
    public String index( ){
        return "Olá mundo";
    }
}
