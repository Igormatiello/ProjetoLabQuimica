package br.edu.utfpr.pb.labquimica.backend.model;

import br.edu.utfpr.pb.labquimica.backend.utils.DefaultFields;
import br.edu.utfpr.pb.labquimica.backend.utils.ValidationMessages;
import br.edu.utfpr.pb.labquimica.backend.utils.serializers.LocalDateDeserializer;
import br.edu.utfpr.pb.labquimica.backend.utils.serializers.LocalDateSerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "credito_professor")
@EqualsAndHashCode(of = "id")
public class CreditoProfessor implements Serializable {
    private static final long serialVersionUID = -8727132008984758972L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne()
    @JoinColumn(name = "pessoa_id", referencedColumnName = "id")
    @NotNull(message = ValidationMessages.PessoaNaoPodeSerVazio)
    private Pessoa pessoa;

    @Column(length = DefaultFields.DESCRICAO, nullable = false)
    @NotBlank(message = ValidationMessages.DescricaoNaoPodeSerVazio)
    private String nomeProjeto;

    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate dataInclusao;

    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate dataAtualizacao;

    @Column(nullable = false)
    @NotNull(message = "Valor do crédito não pode ser vazio")
    private Double valorSaldo = 0.0;

    @Column(nullable = false)
    @NotNull(message = "Valor do crédito não pode ser vazio")
    private Double valorCredito = 0.0;
}
