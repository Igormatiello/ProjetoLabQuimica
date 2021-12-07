package br.edu.utfpr.pb.labquimica.backend.model;

import br.edu.utfpr.pb.labquimica.backend.converter.BooleanConverter;
import br.edu.utfpr.pb.labquimica.backend.enumerators.TipoLancamento;
import br.edu.utfpr.pb.labquimica.backend.enumerators.UnidadeMedidaCobrancaEquipamento;
import br.edu.utfpr.pb.labquimica.backend.utils.DefaultFields;
import br.edu.utfpr.pb.labquimica.backend.utils.PGTypes;
import br.edu.utfpr.pb.labquimica.backend.utils.ValidationMessages;
import br.edu.utfpr.pb.labquimica.backend.utils.serializers.LocalDateDeserializer;
import br.edu.utfpr.pb.labquimica.backend.utils.serializers.LocalDateSerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.web.context.request.FacesRequestAttributes;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "lancamentoFinanceiro")
@EqualsAndHashCode(of = "id")
public class LancamentoFinanceiro implements Serializable {

    private static final long serialVersionUID = -5081207612645573073L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne()
    @JoinColumn(name = "formulario_id", referencedColumnName = "id")
    private Formulario formulario;

    @Column(nullable = false, updatable = false)
    @CreatedDate
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate dataCriacao;

    @Column(nullable = false, updatable = false)
    @CreatedDate
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    @NotNull(message = ValidationMessages.DataLancamentoNaoPodeSerVazio)
    private LocalDate dataLancamento;

    @Column(nullable = false)
    @NotNull(message = ValidationMessages.QuantidadeNaoPodeSerVazio)
    private int quantidade;

    @Column(nullable = false)
    @DecimalMin(message = ValidationMessages.ValorNaoPodeSerVazio, inclusive = false, value = "0.0")
    private Double valor;

    @Column(nullable = true)
    @Enumerated(EnumType.STRING)
    private UnidadeMedidaCobrancaEquipamento unidadeMedida;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    @NotNull(message = ValidationMessages.TipoLancamentoNaoPodeSerVazio)
    private TipoLancamento tipoLancamento;

    @Column(length = DefaultFields.DESCRICAO, nullable = false)
    @NotBlank(message = ValidationMessages.DescricaoNaoPodeSerVazio)
    private String descricao;

    @Column(columnDefinition = PGTypes.Text, nullable = true)
    private String observacao;

    @Column(length = DefaultFields.NOTA_FISCAL, nullable = true)
    private String numeroNotaFiscal;

    @Convert(converter = BooleanConverter.class)
    @Column(columnDefinition = "char(1) default 'N'")
    private boolean ehCancelada;

}





