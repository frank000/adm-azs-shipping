package io.github.frank000.clientes.rest;

import io.github.frank000.clientes.model.entity.Cliente;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;



import java.net.URI;
import java.net.URISyntaxException;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ClienteHTTPTest {
    
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate testRestTemplate;



    private Cliente cliente;
    @Test
    @Order(1)
    public void criar() throws URISyntaxException {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        URI url = new URI("http://localhost:" + port + "/api/clientes");
        cliente = new Cliente();
        cliente.setEmail("Krishna");
        cliente.setNome("krishna@gmail.com");
        cliente.setTelefone("61 984598456");

        HttpEntity<Cliente> requestEntity = new HttpEntity<>(cliente, headers);

        ResponseEntity<Cliente> clienteResponseEntity = testRestTemplate.postForEntity(url, requestEntity, Cliente.class);
        cliente = clienteResponseEntity.getBody();
        assertEquals(clienteResponseEntity.getStatusCode(), HttpStatus.CREATED);
    }

    @Test
    @Order(2)
    public void listar() {
        ResponseEntity<String> forEntity = testRestTemplate.getForEntity("http://localhost:" + port + "/api/clientes", String.class);
        assertEquals(forEntity.getStatusCode(), HttpStatus.OK);
    }

    @Test
    @Order(3)
    public void listarIdInexistente() {
        String id = "999999";
        ResponseEntity<String> forEntity = testRestTemplate.getForEntity("http://localhost:" + port + "/api/clientes/"+id, String.class);
        assertEquals(forEntity.getStatusCode(), HttpStatus.NOT_FOUND);
    }

    @Test
    @Order(4)
    public void delete() throws Exception {
         testRestTemplate.delete("http://localhost:" + port + "/api/clientes/"+ cliente.getId().toString());
    }

}
