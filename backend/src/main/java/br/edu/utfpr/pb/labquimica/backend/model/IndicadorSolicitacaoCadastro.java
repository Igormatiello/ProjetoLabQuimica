package br.edu.utfpr.pb.labquimica.backend.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class IndicadorSolicitacaoCadastro {

    private String categoria;
    private Integer qtdeSolicitacao;
}
