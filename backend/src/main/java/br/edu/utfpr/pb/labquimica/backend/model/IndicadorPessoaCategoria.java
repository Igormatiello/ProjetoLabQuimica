package br.edu.utfpr.pb.labquimica.backend.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class IndicadorPessoaCategoria {

    private String categoria;
    private Integer qtdePessoaCadastrada;
}
