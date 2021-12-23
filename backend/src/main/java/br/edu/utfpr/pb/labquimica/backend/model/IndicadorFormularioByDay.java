package br.edu.utfpr.pb.labquimica.backend.model;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data

@NoArgsConstructor
public class IndicadorFormularioByDay {

    private Long qtde;
    private LocalDate dataSolicitacao;

    // criei porque por algum motivo estava indo uma formato estranho o localDate,
    // dessa forma eu dou um toString antes de mandar pro front
    private String dataSolic;

    public IndicadorFormularioByDay(Long qtde, LocalDate dataSolicitacao) {
        this.qtde = qtde;
        this.dataSolicitacao = dataSolicitacao;
    }

}
