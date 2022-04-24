package io.github.frank000.clientes.service;

import ch.qos.logback.core.net.server.Client;
import io.github.frank000.clientes.model.entity.Carga;
import io.github.frank000.clientes.model.entity.Cliente;
import io.github.frank000.clientes.model.entity.Frete;
import io.github.frank000.clientes.model.entity.Veiculo;
import io.github.frank000.clientes.model.repository.CargaRepository;
import io.github.frank000.clientes.model.repository.ClienteRepository;
import io.github.frank000.clientes.model.repository.FreteRepository;
import io.github.frank000.clientes.model.repository.VeiculoRepository;
import io.github.frank000.clientes.rest.dto.CargaDTO;
import io.github.frank000.clientes.rest.dto.FreteDTO;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.BDDMockito.given;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class FreteServiceTest {

    @Mock
    private FreteRepository repository;

    @Mock
    private ClienteRepository clienteRepository;

    @Mock
    private VeiculoRepository veiculoRepository;

    @Mock
    private CargaRepository cargaRepository;


    @Mock
    private FreteService freteService;

    private Frete frete;

    private  Cliente cli;

    private Veiculo veicu;

    private Carga carga;

    private List<CargaDTO> cargaDTO = new ArrayList<>();

    private List<Carga> listCarga = new ArrayList<>();
    @BeforeEach
    void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
        //employeeRepository = Mockito.mock(EmployeeRepository.class);
        //employeeService = new EmployeeServiceImpl(employeeRepository);
        cli = Cliente.builder()
                .id(1)
                .nome("Fulano Silva")
                .email("fulano@gmail.com")
                .telefone("61 98453216")
                .build();
        veicu = Veiculo.builder()
                .id(1)
                .placa("OFF5F44")
                .descricao("Nissan Cargo")
                .capacidade(1500)
                .build();
        carga = Carga.builder()
                .id(1)
                .altura(2D)
                .largura(2D)
                .profundidade(2D)
                .valor(2500)
                .seguro(true)
                .build();

        listCarga.add(carga);
        cargaDTO.add(
                CargaDTO.builder()
                        .id(carga.getId())
                        .descricao(carga.getDescricao())
                        .altura(carga.getAltura())
                        .largura(carga.getLargura())
                        .profundidade(carga.getProfundidade())
                        .seguro(carga.getSeguro().toString())
                        .valor(carga.getValor().toString())
                        .build()
        );


        frete = Frete.builder()
                .id(1)
                .cliente(cli)
                .veiculo(veicu)
                .cargas(listCarga)
                .build();
    }

    @Test @Order(1)
    public void gimployeeObject_whenSaveEmployee_thenReturnEmployeeObject() throws Exception {

        FreteDTO freteDTO = new FreteDTO();

        freteDTO.setTipo(frete.getTipo());
        freteDTO.setVeiculo(frete.getVeiculo().getId().toString());
        freteDTO.setCliente(cli.getId().toString());
        freteDTO.setCarga(cargaDTO);

        Mockito.when(clienteRepository
                .findById(cli.getId())).thenReturn(Optional.ofNullable(cli));

        Mockito.when(veiculoRepository
                        .findById(veicu.getId()))
                .thenReturn(Optional.ofNullable(veicu));

        List<Frete> collect = Stream.of(frete)
                .collect(Collectors.toList());

        Mockito
                .when(freteService.salvar(freteDTO))
                .thenReturn(frete);

        Frete savedfrete = freteService.salvar(freteDTO);


        assertThat(savedfrete).isNotNull();
        Mockito
                .verify(freteService, Mockito.times(1))
                .salvar(freteDTO);
    }

}
