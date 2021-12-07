package br.edu.utfpr.pb.labquimica.backend.model;

import br.edu.utfpr.pb.labquimica.backend.utils.DefaultFields;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Table(name = "confEmail")
public class ConfEmail implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = DefaultFields.DESCRICAO, nullable = false)
    private String host;

    @Column(nullable = false)
    private Integer porta;

    @Column(length = DefaultFields.DESCRICAO, nullable = false)
    private String remetente;

    @Column(length = DefaultFields.DESCRICAO, nullable = false)
    private String usuario;

    @Column(length = DefaultFields.DESCRICAO, nullable = false)
    private String senha;

    @Column(length = DefaultFields.DESCRICAO, nullable = false)
    private String emailRecebimento;
}
