package io.github.frank000.clientes.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

import javax.persistence.Column;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CargaDTO {

    private String descricao;

    @Nullable
    private Integer id;

    @Nullable
    private String valor;

    private String seguro;

    @Nullable
    private Double altura;

    @Nullable
    private Double largura;

    @Nullable
    private Double profundidade;

    @Nullable
    private Integer peso;

}
