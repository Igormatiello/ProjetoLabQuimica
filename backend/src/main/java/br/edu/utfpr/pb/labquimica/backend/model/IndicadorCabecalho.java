package br.edu.utfpr.pb.labquimica.backend.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class IndicadorCabecalho {

    private Integer qtdeFormularioEmEspera;
    private Integer qtdeFormularioEmAnalise;
    private Integer qtdeFormularioEncerrado;
}
