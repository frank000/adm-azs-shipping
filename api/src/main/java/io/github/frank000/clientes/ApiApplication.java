package io.github.frank000.clientes;

import io.github.frank000.clientes.model.entity.Cliente;
import io.github.frank000.clientes.model.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ApiApplication {

//    @Bean
//    public CommandLineRunner run(@Autowired ClienteRepository repository){
//        return args -> {
//            Cliente cliente = Cliente.builder().cpf("03424754102").nome("Fulano").build();
//            repository.save(cliente);
//
//        };
//    }


    public static void main(String[] args) {
        SpringApplication.run(ApiApplication.class, args);
    }


}
