package br.edu.utfpr.pb.labquimica.backend.model;

import br.edu.utfpr.pb.labquimica.backend.enumerators.TipoAnexoFormulario;
import br.edu.utfpr.pb.labquimica.backend.utils.DefaultFields;
import br.edu.utfpr.pb.labquimica.backend.utils.PGTypes;
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
@Table
@EqualsAndHashCode(of = "id")
public class AnexoFormulario implements Serializable {
	private static final long serialVersionUID = -3958879063750258062L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne()
	@NotNull(message = ValidationMessages.FormularioNaoPodeSerVazio)
	@JoinColumn(name = "formulario_id", referencedColumnName = "id")
	private Formulario formulario;

	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	@NotNull(message = ValidationMessages.TipoAnexoNaoPodeSerVazio)
	private TipoAnexoFormulario tipoAnexo;

	@Column(length = DefaultFields.DESCRICAO, nullable = false)
	@NotBlank(message = ValidationMessages.DescricaoNaoPodeSerVazio)
	private String descricao;

	@Column(nullable = false)
	@JsonDeserialize(using = LocalDateDeserializer.class)
	@JsonSerialize(using = LocalDateSerializer.class)
	@NotNull(message = ValidationMessages.DataUploadNaoPodeSerVazio)
	private LocalDate dataUpload;

	@Column(columnDefinition = PGTypes.Text, nullable = false)
	@NotBlank(message = ValidationMessages.CaminhoUploadNaoPodeSerVazio)
	private String caminho;
}
