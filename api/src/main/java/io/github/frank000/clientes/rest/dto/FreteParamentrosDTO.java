package io.github.frank000.clientes.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Optional;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FreteParamentrosDTO {

    @Nullable
    private String cliente;

    @Nullable
    private String veiculo;

    @Nullable
    private String carga;

    @Nullable
    private String placa;

    public Optional<String> getCliente() {
        return cliente!= null && !cliente.isEmpty() ? Optional.of(cliente) : Optional.empty();
    }
    public Optional<String> getVeiculo() {
        return veiculo!= null && !veiculo.isEmpty() ? Optional.of(veiculo) : Optional.empty();
    }
    public Optional<String> getCarga() {
        return carga!= null && !carga.isEmpty() ? Optional.of(carga) : Optional.empty();
    }
    public Optional<String> getPlaca() {
        return placa!= null && !placa.isEmpty() ? Optional.of(placa) : Optional.empty();
    }
}
