package br.edu.utfpr.pb.labquimica.backend.testes.controller;

import java.time.LocalDate;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.Test;

import br.edu.utfpr.pb.labquimica.backend.model.IndicadorCabecalho;
import br.edu.utfpr.pb.labquimica.backend.model.IndicadorFormularioByDay;
import br.edu.utfpr.pb.labquimica.backend.model.IndicadorPessoaCategoria;
import br.edu.utfpr.pb.labquimica.backend.model.IndicadorSolicitacaoCadastro;
import br.edu.utfpr.pb.labquimica.backend.model.Indicadores;
import br.edu.utfpr.pb.labquimica.backend.service.HomeService;

public class HomeControllerTests {

	HomeService service;

	public static IndicadorCabecalho criarIndicadorCabecalho() {

		return IndicadorCabecalho.builder().qtdeFormularioEmAnalise(1).qtdeFormularioEmEspera(0)
				.qtdeFormularioEncerrado(0).build();

	}

	public static IndicadorSolicitacaoCadastro criarIndicadorSolicitacaoCadastro() {

		return IndicadorSolicitacaoCadastro.builder().qtdeSolicitacao(2).categoria("categoria solicitacao cadastro")
				.build();
	}

	public static IndicadorPessoaCategoria criarIndicadorPessoaCategoria() {

		return IndicadorPessoaCategoria.builder().categoria("categoria pessoa categoria").qtdePessoaCadastrada(3)
				.build();

	}
	/*
	 * @Test public void teste() {
	 * 
	 * 
	 * Indicadores indicadores= new Indicadores(); IndicadorCabecalho
	 * indicadorCabecalho= new IndicadorCabecalho(1, 1, 1);
	 * indicadores.setIndicadorCabecalho(indicadorCabecalho);
	 * IndicadorFormularioByDay indicadorFByDay= new IndicadorFormularioByDay(2l,
	 * LocalDate.of(1, 1, 1), "");
	 * 
	 * List<IndicadorFormularioByDay> lista1; lista1.add(indicadorFByDay);
	 * 
	 * indicadores.setIndicadorFormularioByDayList(lista1);
	 * 
	 * 
	 * IndicadorPessoaCategoria indicadorPCategoria= new
	 * IndicadorPessoaCategoria("categoria", 2);
	 * 
	 * List<IndicadorPessoaCategoria> lista2; lista2.add(indicadorPCategoria);
	 * 
	 * indicadores.setIndicadorPessoaCategoriaList(lista2);
	 * 
	 * IndicadorSolicitacaoCadastro indicadorSC= new
	 * IndicadorSolicitacaoCadastro("categoria", 2);
	 * 
	 * List<IndicadorSolicitacaoCadastro> lista3;
	 * 
	 * lista3.add(indicadorSC);
	 * 
	 * indicadores.setIndicadorSolicitacaoCadastroList(lista3);
	 * 
	 * 
	 * IndicadorSolicitacaoCadastro indicadorSC2= new
	 * IndicadorSolicitacaoCadastro("categoria", 1);
	 * 
	 * List<IndicadorSolicitacaoCadastro> lista4;
	 * 
	 * lista4.add(indicadorSC2);
	 * 
	 * 
	 * //indicadores.setSolicitacaoCadastrosPendentes(lista4);
	 * 
	 * // Indicadores indicadores = service.findDadosIndicadores(null, null);
	 * 
	 * // Assertions.assertThat(indicadores.getSolicitacaoCadastrosPendentes()).
	 * contains(SC);
	 * 
	 * 
	 * }
	 */

}
