package io.github.frank000.clientes.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

import java.util.Optional;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VeiculoParamentrosDTO {

    @Nullable
    private String placa;

    @Nullable
    private String veiculo;

    @Nullable
    private String carga;


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
