package br.edu.utfpr.pb.labquimica.backend.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Indicadores {

	private IndicadorCabecalho indicadorCabecalho;
	private List<IndicadorSolicitacaoCadastro> indicadorSolicitacaoCadastroList;
	private List<IndicadorPessoaCategoria> indicadorPessoaCategoriaList;
	private List<SolicitacaoCadastro> solicitacaoCadastrosPendentes;
	private List<IndicadorFormularioByDay> indicadorFormularioByDayList;
}
