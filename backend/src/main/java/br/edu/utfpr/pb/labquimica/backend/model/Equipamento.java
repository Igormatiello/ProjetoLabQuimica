package br.edu.utfpr.pb.labquimica.backend.model;

import br.edu.utfpr.pb.labquimica.backend.enumerators.UnidadeMedidaCobrancaEquipamento;
import br.edu.utfpr.pb.labquimica.backend.utils.DefaultFields;
import br.edu.utfpr.pb.labquimica.backend.utils.PGTypes;
import br.edu.utfpr.pb.labquimica.backend.utils.ValidationMessages;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Set;

@Entity
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "equipamento")
@EqualsAndHashCode(of = "id")
public class Equipamento implements Serializable {
    private static final long serialVersionUID = -8727132008984758268L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = ValidationMessages.NomeNaoPodeSerVazio)
    @Column(length = DefaultFields.DESCRICAO, nullable = false)
    private String nome;

    @NotBlank(message = ValidationMessages.SiglaNaoPodeSerVazio)
    @Column(length = DefaultFields.SIGLA, nullable = false)
    private String sigla;

    @Column(columnDefinition = PGTypes.Text)
    private String metodologia;

    @Column(columnDefinition = PGTypes.Text)
    private String laudoPadrao;

    @Column(columnDefinition = PGTypes.Text)
    private String observacoesFixas;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private UnidadeMedidaCobrancaEquipamento unidadeMedidaInterno;

    @Column(nullable = false)
    @NotNull(message = ValidationMessages.ValorInternoNaoPodeSerVazio)
    @DecimalMin(message = ValidationMessages.ValorInternoNaoPodeSerVazio, value = "0.0", inclusive = false)
    private Double valorInterno;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private UnidadeMedidaCobrancaEquipamento unidadeMedidaExterno;

    @Column(nullable = false)
    @NotNull(message = ValidationMessages.ValorExternoNaoPodeSerVazio)
    @DecimalMin(message = ValidationMessages.ValorExternoNaoPodeSerVazio, value = "0.0", inclusive = false)
    private Double valorExterno;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private UnidadeMedidaCobrancaEquipamento unidadeMedidaPadrao;

    @Column(nullable = false)
    @NotNull(message = ValidationMessages.ValorPadraoNaoPodeSerVazio)
    @DecimalMin(message = ValidationMessages.ValorPadraoNaoPodeSerVazio, value = "0.0", inclusive = false)
    private Double valorPadrao;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "equipamento")
    private Set<Servico> servicos;
}



