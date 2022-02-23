package br.edu.utfpr.pb.labquimica.backend.model;

import br.edu.utfpr.pb.labquimica.backend.enumerators.TipoInstituicao;
import br.edu.utfpr.pb.labquimica.backend.utils.DefaultFields;
import br.edu.utfpr.pb.labquimica.backend.utils.ValidationMessages;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
@Table(name = "instituicao")
@EqualsAndHashCode(of = "id")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class Instituicao implements Serializable {
	private static final long serialVersionUID = -8727132008984758268L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@NotBlank(message = ValidationMessages.NomeNaoPodeSerVazio)
	@Column(length = DefaultFields.DESCRICAO, nullable = false)
	private String nome;

	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	@NotNull(message = ValidationMessages.TipoInsituicaoNaoPodeSerVazio)
	private TipoInstituicao tipoInstituicao;

	@ManyToOne()
	@NotNull(message = ValidationMessages.CidadeNaoPodeSerVazio)
	@JoinColumn(name = "cidade_id", referencedColumnName = "id")
	private Cidade cidade;
}
