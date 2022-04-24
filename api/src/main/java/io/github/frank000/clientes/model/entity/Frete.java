package io.github.frank000.clientes.model.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "frete")
@JsonIdentityInfo(generator= ObjectIdGenerators.UUIDGenerator.class, property="@id")
@Where(clause = DefaultTable.ATIVO)
@SQLDelete(sql = "UPDATE frete SET " + DefaultTable.NAO_ATIVO + " WHERE id = ?")
public class Frete extends DefaultTable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "id_cliente")
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "id_veiculo")
    private Veiculo veiculo;

    @OneToMany(mappedBy = "frete", cascade = CascadeType.ALL)
    private List<Carga> cargas = new ArrayList<>();

    @Column(name = "flg_tipo",nullable = false, length = 1)
    @NotNull(message = "{campo.cpf.obrigatorio}")
    private String tipo;

    @Transient
    private Double totalCubagem;

//    @Column(name = "data_cadastro", updatable = false)
//    @JsonFormat(pattern = "dd/MM/yyyy")
//    private LocalDate dataCadastro;
//
//    @PrePersist
//    public void prePersist(){
//        setDataCadastro(LocalDate.now());
//    }

    public Double getTotalCubagem() {
        List<Double> collect = this.getCargas().stream()
                .map(x -> {
                    if (x.getCubagem()== null) return Double.parseDouble("0");
                    return x.getCubagem();
                })
                .collect(Collectors.toList());

        return collect.stream().mapToDouble(Double::doubleValue).sum();
    }
}
