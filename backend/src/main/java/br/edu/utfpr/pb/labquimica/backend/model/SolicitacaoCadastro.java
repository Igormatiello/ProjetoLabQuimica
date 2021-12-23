package br.edu.utfpr.pb.labquimica.backend.model;

import br.edu.utfpr.pb.labquimica.backend.converter.BooleanConverter;
import br.edu.utfpr.pb.labquimica.backend.enumerators.StatusSolicitante;
import br.edu.utfpr.pb.labquimica.backend.enumerators.TipoPessoa;
import br.edu.utfpr.pb.labquimica.backend.utils.DefaultFields;
import br.edu.utfpr.pb.labquimica.backend.utils.ValidationMessages;
import br.edu.utfpr.pb.labquimica.backend.utils.serializers.LocalDateDeserializer;
import br.edu.utfpr.pb.labquimica.backend.utils.serializers.LocalDateSerializer;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.*;
import javax.validation.constraints.Email;
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
@Table
@EqualsAndHashCode(of = "id")
@EntityListeners(AuditingEntityListener.class)
public class SolicitacaoCadastro implements Serializable {
    private static final BCryptPasswordEncoder bCrypts = new BCryptPasswordEncoder(10);

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = DefaultFields.SENHA, nullable = false)
    @NotBlank(message = ValidationMessages.SenhaNaoPodeSerVazio)
    @JsonProperty( value = "senha", access = JsonProperty.Access.WRITE_ONLY)
    private String senha;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    @NotNull(message = ValidationMessages.TipoPessoaNaoPodeSerVazio)
    private TipoPessoa tipoPessoa;

    @ManyToOne()
    @NotNull(message = ValidationMessages.CidadeNaoPodeSerVazio)
    @JoinColumn(name = "cidade_id", referencedColumnName = "id")
    private Cidade cidade;

    @NotBlank(message = ValidationMessages.NomeNaoPodeSerVazio)
    @Column(length = DefaultFields.DESCRICAO, nullable = false)
    private String nome;

    @NotBlank(message = ValidationMessages.DocumentoNaoPodeSerVazio)
    @Column(length = DefaultFields.CNPJ, nullable = false)
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

    @Column(length = DefaultFields.NOMEPROJETO, nullable = true)
    private String nomeprojeto;

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

    @CreatedDate
    @Column(updatable = false)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate dataCriacao;

    @Column(nullable = true, length = DefaultFields.DESCRICAO)
    private String nomeInstituicao;

    @Column(length = DefaultFields.CPF, nullable = true)
    private String cpfOrientador;

    @Column(length = DefaultFields.DESCRICAO_CURTA, nullable = true)
    private String nomeProgramaEnsino;

    @Convert(converter = BooleanConverter.class)
    @Column(columnDefinition = "char(1) default 'N'")
    private boolean ehProfessor;

    @Column(nullable = true)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    @NotNull(message = ValidationMessages.DataTerminoProgramaEnsinoNaoPodeSerVazia)
    private LocalDate dataTerminoProgramaEnsino;

    @Column(length = DefaultFields.INSCRICAO_ESTADUAL, nullable = true)
    private String inscricaoEstadual;

    @OneToOne(fetch=FetchType.LAZY, mappedBy="solicitacaoCadastro")
    private Pessoa pessoa;

    public String getSenhaCripto(String pass) {
        if (pass != null && !pass.equals("")) {
            return bCrypts.encode(this.getSenha());
        }
        return pass;
    }

    @PrePersist
    public void prePersist() {
        if (getId() == null || getId() == 0) {
            setSenha(getSenhaCripto(getSenha()));
        }
    }
}





