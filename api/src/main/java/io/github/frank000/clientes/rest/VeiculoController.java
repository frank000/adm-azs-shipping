package io.github.frank000.clientes.rest;


import io.github.frank000.clientes.model.entity.Frete;
import io.github.frank000.clientes.model.entity.Veiculo;
import io.github.frank000.clientes.model.repository.CargaRepository;
import io.github.frank000.clientes.model.repository.FreteRepository;
import io.github.frank000.clientes.model.repository.VeiculoRepository;
import io.github.frank000.clientes.rest.dto.FreteDTO;
import io.github.frank000.clientes.rest.dto.FreteParamentrosDTO;
import io.github.frank000.clientes.rest.dto.VeiculoParamentrosDTO;
import io.github.frank000.clientes.service.FreteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/veiculos")
public class VeiculoController {

    @Autowired
    private VeiculoRepository repository;


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Veiculo salvar(@RequestBody @Valid Veiculo veiculo) throws Exception {


        return repository.save(veiculo);
    }

    @GetMapping("{id}")
    public Veiculo acharPorId(@PathVariable @Valid Integer id){
        return repository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @GetMapping
    public List<Veiculo> listar(@RequestBody(required = false) VeiculoParamentrosDTO dto){
        List<Veiculo> all = repository.list(dto);
        return all;
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Integer id){
        repository
                .findById(id)
                .map( veiculo -> {
                    veiculo.setAtivo(false);
                    repository.save(veiculo);
                    return Void.TYPE;
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void alterarVeiculo(@PathVariable Integer id,@RequestBody @Valid VeiculoParamentrosDTO veiculo){
        repository
                .findById(id)
                .map( veiculo1 -> {
                    veiculo.getVeiculo().ifPresent(x ->  veiculo1.setDescricao(x) );
                    veiculo.getPlaca().ifPresent(x ->  veiculo1.setPlaca(x) );
                    veiculo.getCarga().ifPresent(x ->  veiculo1.setCapacidade(Integer.parseInt(x)) );

                    return repository.save(veiculo1);
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }
}
