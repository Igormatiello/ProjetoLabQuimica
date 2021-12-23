package br.edu.utfpr.pb.labquimica.backend.model;

import br.edu.utfpr.pb.labquimica.backend.converter.BooleanConverter;
import br.edu.utfpr.pb.labquimica.backend.utils.ValidationMessages;
import br.edu.utfpr.pb.labquimica.backend.utils.serializers.LocalDateDeserializer;
import br.edu.utfpr.pb.labquimica.backend.utils.serializers.LocalDateSerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
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
public class ParticipacaoProgramaEnsino implements Serializable {
    private static final long serialVersionUID = -8727132008984758268L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne()
    @JoinColumn(name = "programaensino_id", referencedColumnName = "id")
    @NotNull(message = ValidationMessages.ProgramaEnsinoNaoPodeSerVazio)
    private ProgramaEnsino programaEnsino;

    @ManyToOne(optional = true)
    @JoinColumn(name = "participante_id", referencedColumnName = "id", nullable = true)
    private PessoaInstituicao participante;

    @ManyToOne()
    @JoinColumn(name = "orientador_id", referencedColumnName = "id", nullable = true)
    private PessoaInstituicao orientador;

    @Column(nullable = false)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate dataTermino;

    @Convert(converter = BooleanConverter.class)
    @Column(columnDefinition = "char(1) default 'S'")
    private boolean ehAtivo;

}





