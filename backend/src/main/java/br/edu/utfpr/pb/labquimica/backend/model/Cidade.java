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
@Table(name = "cidade")
@EqualsAndHashCode(of = "id")
public class Cidade implements Serializable {
    private static final long serialVersionUID = -8727132008984758268L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = ValidationMessages.NomeNaoPodeSerVazio)
    @Column(length = DefaultFields.DESCRICAO, nullable = false)
    private String nome;

    @NotBlank(message = ValidationMessages.UfNaoPodeSerVazio)
    @Column(length = DefaultFields.UF, nullable = false)
    private String uf;
}



