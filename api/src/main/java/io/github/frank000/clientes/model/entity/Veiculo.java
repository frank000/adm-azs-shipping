package io.github.frank000.clientes.model.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "veiculo")
@JsonIdentityInfo(generator= ObjectIdGenerators.UUIDGenerator.class, property="@id")
@Where(clause = DefaultTable.ATIVO)
@SQLDelete(sql = "UPDATE frete SET " + DefaultTable.NAO_ATIVO + " WHERE id = ?")
public class Veiculo extends DefaultTable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 200)
    @NotEmpty(message = "{campo.nome.obrigatorio}")
    private String descricao;

    @Column(nullable = false, length = 11)
    @NotNull(message = "{campo.cpf.obrigatorio}")
    private String placa;

    @Column(nullable = false, length = 11)
    @NotNull(message = "{campo.cpf.obrigatorio}")
    private Integer capacidade;

    @OneToMany(mappedBy = "veiculo", cascade = CascadeType.ALL)
    @JsonIgnore
    private final List<Frete> fretes = new ArrayList<>();

//    @Column(name = "data_cadastro", updatable = false)
//    @JsonFormat(pattern = "dd/MM/yyyy")
//    private LocalDate dataCadastro;
//
//    @PrePersist
//    public void prePersist(){
//        setDataCadastro(LocalDate.now());
//    }
}
