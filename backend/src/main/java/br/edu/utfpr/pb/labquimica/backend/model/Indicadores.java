package br.edu.utfpr.pb.labquimica.backend.model;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class Indicadores {

    private IndicadorCabecalho indicadorCabecalho;
    private List<IndicadorSolicitacaoCadastro> indicadorSolicitacaoCadastroList;
    private List<IndicadorPessoaCategoria> indicadorPessoaCategoriaList;
    private List<SolicitacaoCadastro> solicitacaoCadastrosPendentes;
    private List<IndicadorFormularioByDay> indicadorFormularioByDayList;
}
