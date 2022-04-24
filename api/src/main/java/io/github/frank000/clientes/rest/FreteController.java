package io.github.frank000.clientes.rest;


import io.github.frank000.clientes.model.entity.Carga;
import io.github.frank000.clientes.model.entity.Cliente;
import io.github.frank000.clientes.model.entity.Frete;
import io.github.frank000.clientes.model.repository.CargaRepository;
import io.github.frank000.clientes.model.repository.ClienteRepository;
import io.github.frank000.clientes.model.repository.FreteRepository;
import io.github.frank000.clientes.rest.dto.FreteDTO;
import io.github.frank000.clientes.rest.dto.FreteParamentrosDTO;
import io.github.frank000.clientes.service.FreteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/fretes")
public class FreteController {

    @Autowired
    private FreteRepository repository;

    @Autowired
    private CargaRepository cargaRepository;

    @Autowired
    private FreteService freteService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Frete salvar(@RequestBody @Valid FreteDTO freteDTO) throws Exception {


        return freteService.salvar(freteDTO);
    }

    @GetMapping("{id}")
    public Frete acharPorId(@PathVariable @Valid Integer id){
        return repository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @GetMapping
    public List<Frete> listarFretes(@RequestBody FreteParamentrosDTO frete){
        List<Frete> all = repository.list(frete);
        return all;
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Integer id){
        repository
                .findById(id)
                .map( frete -> {
                    repository.delete(frete);
                    return Void.TYPE;
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void alterarCliente(@PathVariable Integer id,@RequestBody @Valid Frete freteAtual){
        repository
                .findById(id)
                .map( fre -> {
                    freteAtual.setId(fre.getId());
                    return repository.save(freteAtual);
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }
}
