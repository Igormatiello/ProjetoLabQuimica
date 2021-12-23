package br.edu.utfpr.pb.labquimica.backend.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class IndicadorSolicitacaoCadastro {

    private String categoria;
    private Integer qtdeSolicitacao;
}
