package br.edu.utfpr.pb.labquimica.backend.model;

import br.edu.utfpr.pb.labquimica.backend.converter.BooleanConverter;
import br.edu.utfpr.pb.labquimica.backend.enumerators.TipoPessoa;
import br.edu.utfpr.pb.labquimica.backend.utils.DefaultFields;
import br.edu.utfpr.pb.labquimica.backend.utils.ValidationMessages;
import br.edu.utfpr.pb.labquimica.backend.utils.serializers.LocalDateDeserializer;
import br.edu.utfpr.pb.labquimica.backend.utils.serializers.LocalDateSerializer;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "pessoa")
@EqualsAndHashCode(of = "id")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@EntityListeners(AuditingEntityListener.class)
public class Pessoa implements Serializable {

    private static final long serialVersionUID = -351465541723389912L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    @NotNull(message = ValidationMessages.TipoPessoaNaoPodeSerVazio)
    private TipoPessoa tipoPessoa;

    @NotBlank(message = ValidationMessages.NomeNaoPodeSerVazio)
    @Column(length = DefaultFields.DESCRICAO, nullable = false)
    private String nome;

    @NotBlank(message = ValidationMessages.DocumentoNaoPodeSerVazio)
    @Column(length = DefaultFields.CNPJ, nullable = false, updatable = false)
    private String documento;

    @NotBlank(message = ValidationMessages.CepNaoPodeSerVazio)
    @Column(length = DefaultFields.CEP, nullable = false)
    private String cep;

    @NotBlank(message = ValidationMessages.EnderecoNaoPodeSerVazio)
    @Column(length = DefaultFields.DESCRICAO, nullable = false)
    private String endereco;

    @NotBlank(message = ValidationMessages.BairroNaoPodeSerVazio)
    @Column(length = DefaultFields.DESCRICAO_CURTA, nullable = false)
    private String bairro;

    @NotBlank(message = ValidationMessages.NumeroEnderecoNaoPodeSerVazio)
    @Column(length = DefaultFields.ENDERECO_NUMERO, nullable = false)
    private String numero;

    @Column(length = DefaultFields.COMPLEMENTO, nullable = true)
    private String complemento;

    @NotBlank(message = ValidationMessages.TelefoneNaoPodeSerVazio)
    @Column(length = DefaultFields.TELEFONE, nullable = false)
    private String telefone;

    @NotBlank(message = ValidationMessages.CelularNaoPodeSerVazio)
    @Column(length = DefaultFields.TELEFONE, nullable = false)
    private String celular;

    @NotBlank(message = ValidationMessages.EmailNaoPodeSerVazio)
    @Email(message = ValidationMessages.EmailNaoPodeSerInvalido)
    @Column(length = DefaultFields.DESCRICAO_CURTA, nullable = false)
    private String email;

    @Column(length = DefaultFields.CNPJ, nullable = true)
    private String inscricaoEstadual;

    @Column
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    @CreatedDate
    private LocalDate dataCriacao;

    @Convert(converter = BooleanConverter.class)
    @Column(columnDefinition = "char(1) default 'S'")
    private boolean ehAtivo;

    @ManyToOne()
    @NotNull(message = ValidationMessages.CidadeNaoPodeSerVazio)
    @JoinColumn(name = "cidade_id", referencedColumnName = "id")
    private Cidade cidade;

    @OneToMany(
            mappedBy = "pessoa",
            targetEntity = PessoaInstituicao.class,
            fetch = FetchType.LAZY,
            cascade = {CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.MERGE, CascadeType.REMOVE})
    @JsonIgnore
    private List<PessoaInstituicao> vinculos;

    @OneToOne(optional = true)
    @JoinColumn(name = "solicitacaocadastro_id", referencedColumnName = "id", nullable = true)
    @JsonIgnore
    private SolicitacaoCadastro solicitacaoCadastro;

    @JoinColumn(name = "usuario_id", referencedColumnName = "id", nullable = true)
    @OneToOne(optional = true)
    @JsonIgnore
    private Usuario usuario;

    @Override
    public String toString() {
        return this.getId().toString();
    }
}





