package io.github.frank000.clientes.service;

import io.github.frank000.clientes.model.entity.Carga;
import io.github.frank000.clientes.model.entity.Cliente;
import io.github.frank000.clientes.model.entity.Frete;
import io.github.frank000.clientes.model.entity.Veiculo;
import io.github.frank000.clientes.model.repository.CargaRepository;
import io.github.frank000.clientes.model.repository.ClienteRepository;
import io.github.frank000.clientes.model.repository.FreteRepository;
import io.github.frank000.clientes.model.repository.VeiculoRepository;
import io.github.frank000.clientes.rest.dto.FreteDTO;
import io.github.frank000.clientes.rest.exception.CustomException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FreteService {

    @Autowired
    public FreteRepository repository;

    @Autowired
    public ClienteRepository clienteRepository;

    @Autowired
    public VeiculoRepository veiculoRepository;

    @Autowired
    public CargaRepository cargaRepository;

    public Frete salvar(FreteDTO freteDTO) throws Exception {
        Optional<Cliente> byId = clienteRepository
                .findById(Integer.parseInt(freteDTO.getCliente()));
        Optional<Veiculo> byIdVei = veiculoRepository.findById(Integer.parseInt(freteDTO.getVeiculo()));
        Frete frete = new Frete();

        byId.map(cliente -> {
                    frete.setCliente(cliente);
                    return Void.TYPE;
                })
                .orElseThrow(()->
                        new CustomException("Cliente não existe."));
        byIdVei.map(veiculo -> {
                    frete.setVeiculo(veiculo);
                    return Void.TYPE;
                })
                .orElseThrow(()->
                        new CustomException("Veiculo não existe."));
        frete.setTipo(freteDTO.getTipo());
        Frete savedFrete = repository.save(frete);


        List<Carga> collect = freteDTO
                .getCarga()
                .stream().map(x -> {
                    Carga carga = new Carga();
                    carga.setDescricao(x.getDescricao());
                    carga.setAltura(x.getAltura());
                    carga.setLargura(x.getLargura());
                    carga.setPeso(x.getPeso());
                    carga.setFrete(savedFrete);
                    carga.setProfundidade(x.getProfundidade());
                    carga.setSeguro(Boolean.valueOf(x.getSeguro()));
                    carga.setValor(Integer.valueOf(x.getValor()));
                    return carga;
                }).collect(Collectors.toList());
        collect
                .stream()
                .map(x-> {
                    cargaRepository.save(x);
                    return Void.TYPE;
                });



        return savedFrete;
    }

}