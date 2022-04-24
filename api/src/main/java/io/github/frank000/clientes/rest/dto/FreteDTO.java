package io.github.frank000.clientes.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FreteDTO {

    @Nullable
    private Integer id;

    @Nullable
    private String cliente;

    @Nullable
    private String veiculo;

    @Nullable
    @NotNull(message = "Tipo não informado (P=Peso / C = Cubagem)")
    private String tipo = "N";

    @NotNull
    private List<CargaDTO> carga;



}
