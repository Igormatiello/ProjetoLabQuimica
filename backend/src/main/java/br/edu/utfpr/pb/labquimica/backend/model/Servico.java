package br.edu.utfpr.pb.labquimica.backend.model;

import br.edu.utfpr.pb.labquimica.backend.utils.DefaultFields;
import br.edu.utfpr.pb.labquimica.backend.utils.ValidationMessages;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table
@EqualsAndHashCode(of = "id")
public class Servico implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne()
    @JoinColumn(name = "equipamento_id", referencedColumnName = "id")
    @NotNull(message = ValidationMessages.EquipamentoNaoPodeSerVazio)
    @JsonProperty( value = "equipamento", access = JsonProperty.Access.WRITE_ONLY)
    private Equipamento equipamento;

    @Column(length = DefaultFields.DESCRICAO, nullable = false)
    @NotBlank(message = ValidationMessages.DescricaoNaoPodeSerVazio)
    private String descricao;
}





