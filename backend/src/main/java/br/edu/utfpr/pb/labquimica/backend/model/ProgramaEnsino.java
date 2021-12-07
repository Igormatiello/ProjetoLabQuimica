package br.edu.utfpr.pb.labquimica.backend.model;

import br.edu.utfpr.pb.labquimica.backend.utils.DefaultFields;
import br.edu.utfpr.pb.labquimica.backend.utils.ValidationMessages;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Entity
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table
@EqualsAndHashCode(of = "id")
public class ProgramaEnsino implements Serializable {
    private static final long serialVersionUID = -3346120304395527353L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = ValidationMessages.NomeNaoPodeSerVazio)
    @Column(length = DefaultFields.DESCRICAO, nullable = false)
    private String nome;

    @NotBlank(message = ValidationMessages.SiglaNaoPodeSerVazio)
    @Column(length = DefaultFields.DESCRICAO_CURTA, nullable = false)
    private String sigla;
}





