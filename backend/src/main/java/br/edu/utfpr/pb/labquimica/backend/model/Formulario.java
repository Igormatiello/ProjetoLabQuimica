package br.edu.utfpr.pb.labquimica.backend.model;

import br.edu.utfpr.pb.labquimica.backend.converter.BooleanConverter;
import br.edu.utfpr.pb.labquimica.backend.enumerators.StatusFormulario;
import br.edu.utfpr.pb.labquimica.backend.utils.DefaultFields;
import br.edu.utfpr.pb.labquimica.backend.utils.PGTypes;
import br.edu.utfpr.pb.labquimica.backend.utils.ValidationMessages;
import br.edu.utfpr.pb.labquimica.backend.utils.serializers.LocalDateDeserializer;
import br.edu.utfpr.pb.labquimica.backend.utils.serializers.LocalDateSerializer;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Entity
@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table
@EqualsAndHashCode(of = "id")
@EntityListeners(AuditingEntityListener.class)
public class Formulario implements Serializable {

	private static final long serialVersionUID = -351465541723389912L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne()
	@JoinColumn(name = "pessoa_id", referencedColumnName = "id")
	@NotNull(message = ValidationMessages.PessoaNaoPodeSerVazio)
	private Pessoa pessoa;

	@ManyToOne()
	@JoinColumn(name = "participacaoprograma_id", referencedColumnName = "id")
	private ParticipacaoProgramaEnsino participacaoPrograma;

	@ManyToOne()
	@JoinColumn(name = "equipamento_id", referencedColumnName = "id")
	@NotNull(message = ValidationMessages.EquipamentoNaoPodeSerVazio)
	private Equipamento equipamento;

	@ManyToMany(fetch = FetchType.EAGER)
	@JsonFormat(with = JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
	private Set<Servico> servico;

	@ManyToOne()
	@JoinColumn(name = "professor_id", referencedColumnName = "id")
	private Pessoa professor;

	@ManyToOne()
	@JoinColumn(name = "credito_professor_id", referencedColumnName = "id")
	private CreditoProfessor creditoProfessor;

	@Column(nullable = false)
	@JsonDeserialize(using = LocalDateDeserializer.class)
	@JsonSerialize(using = LocalDateSerializer.class)
	@CreatedDate
	private LocalDate dataSolicitacao;

	@Column(length = DefaultFields.DESCRICAO_CURTA, nullable = true)
	private String nomeProgramaEnsino;

	@Column(columnDefinition = PGTypes.Text, nullable = true)
	private String naturezaProjeto;

	@Column(nullable = false, length = DefaultFields.DESCRICAO)
	@NotBlank(message = ValidationMessages.NomeServicoNaoPodeSerVazio)
	private String nomeServico;

	@Column(columnDefinition = PGTypes.Text, nullable = false)
	@NotBlank(message = ValidationMessages.MetodologiaNaoPodeSerVazio)
	private String metodologia;

	@Column(columnDefinition = PGTypes.Text, nullable = true)
	private String laudo;

	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	@NotNull(message = ValidationMessages.StatusFormularioNaoPodeSerVazio)
	private StatusFormulario status;

	@Column(nullable = true, columnDefinition = PGTypes.Text)
	private String motivoRecusaAmostra;

	@Convert(converter = BooleanConverter.class)
	@Column(columnDefinition = "char(1) default 'S'")
	private boolean emailEnviadoSolicitante;

	@Convert(converter = BooleanConverter.class)
	@Column(columnDefinition = "char(1) default 'S'")
	private boolean emailEnviadoFuntef;

	@Convert(converter = BooleanConverter.class)
	@Column(columnDefinition = "char(1) default 'S'")
	private boolean analise;

	@Column()
	private Integer numeroAmostras;

	@Column(name = "imagem", length = 1024, nullable = true)
	private String imagem;

	@OneToMany(mappedBy = "formulario", targetEntity = AnexoFormulario.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JsonIgnore
	private Set<AnexoFormulario> anexos;

}
