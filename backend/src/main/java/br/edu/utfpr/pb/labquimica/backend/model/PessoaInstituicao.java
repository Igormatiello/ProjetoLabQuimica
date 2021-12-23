package br.edu.utfpr.pb.labquimica.backend.model;

import br.edu.utfpr.pb.labquimica.backend.converter.BooleanConverter;
import br.edu.utfpr.pb.labquimica.backend.utils.DefaultFields;
import br.edu.utfpr.pb.labquimica.backend.utils.ValidationMessages;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table
@EqualsAndHashCode(of = "id")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class PessoaInstituicao implements Serializable {
    private static final long serialVersionUID = -8727132008984758268L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "instituicao_id", referencedColumnName = "id")
    @NotNull(message = ValidationMessages.InstituicaoNaoPodeSerVazio)
    private Instituicao instituicao;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "pessoa_id", referencedColumnName = "id")
    @NotNull(message = ValidationMessages.PessoaNaoPodeSerVazio)
    private Pessoa pessoa;

    @Convert(converter = BooleanConverter.class)
    @Column(columnDefinition = "char(1) default 'S'")
    private boolean ehAtivo;

    @Column(nullable = true)
    private Double creditoVigente = 0.0;

    @Convert(converter = BooleanConverter.class)
    @Column(columnDefinition = "char(1) default 'N'")
    private boolean ehProfessor;

    @Override
    public String toString(){
        return this.getId().toString();
    }

}





