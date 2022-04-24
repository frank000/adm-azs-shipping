package io.github.frank000.clientes.model.entity;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonValue;
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

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "item_carga")
@JsonIdentityInfo(generator= ObjectIdGenerators.UUIDGenerator.class, property="@id")
@Where(clause = DefaultTable.ATIVO)
@SQLDelete(sql = "UPDATE frete SET " + DefaultTable.NAO_ATIVO + " WHERE id = ?")
public class Carga extends DefaultTable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 150)
    @NotEmpty(message = "{campo.nome.obrigatorio}")
    private String descricao;


    @Column(name = "valor_aprox")
    private Integer valor;

    @Column(nullable = false, name = "flg_seguro")
    private Boolean seguro;

    @Column
    private Double altura;

    @Column
    private Double largura;

    @Column
    private Double profundidade;

    @Column
    private Integer peso;


    @Transient
    private Double cubagem;

    @ManyToOne
    @JoinColumn(name = "id_frete")
    private Frete frete;

//    @Column(name = "data_cadastro", updatable = false)
//    @JsonFormat(pattern = "dd/MM/yyyy")
//    private LocalDate dataCadastro;
//
//    @PrePersist
//    public void prePersist(){
//        setDataCadastro(LocalDate.now());
//    }

    public Double getCubagem() {
        Double result = null;
        if(altura != null && largura != null && profundidade != null){
            Double v = altura * largura * profundidade;
            result = v;
        }

        return result;
    }

    public void setCubagem(Double cubagem) {
        this.cubagem = cubagem;
    }
}
