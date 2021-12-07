package br.edu.utfpr.pb.labquimica.backend.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class IndicadorCabecalho {

    private Integer qtdeFormularioEmEspera;
    private Integer qtdeFormularioEmAnalise;
    private Integer qtdeFormularioEncerrado;
}
