package br.edu.utfpr.pb.labquimica.backend.testes.service;

import java.time.LocalDate;

import org.assertj.core.api.Assertions;
import org.bouncycastle.asn1.dvcs.Data;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import br.edu.utfpr.pb.labquimica.backend.enumerators.StatusFormulario;
import br.edu.utfpr.pb.labquimica.backend.model.Formulario;
import br.edu.utfpr.pb.labquimica.backend.model.IndicadorCabecalho;
import br.edu.utfpr.pb.labquimica.backend.model.IndicadorFormularioByDay;
import br.edu.utfpr.pb.labquimica.backend.model.IndicadorPessoaCategoria;
import br.edu.utfpr.pb.labquimica.backend.model.IndicadorSolicitacaoCadastro;
import br.edu.utfpr.pb.labquimica.backend.model.Indicadores;
import br.edu.utfpr.pb.labquimica.backend.model.ParticipacaoProgramaEnsino;
import br.edu.utfpr.pb.labquimica.backend.model.SolicitacaoCadastro;
import br.edu.utfpr.pb.labquimica.backend.repository.FormularioRepository;
import br.edu.utfpr.pb.labquimica.backend.repository.SolicitacaoCadastroRepository;
import br.edu.utfpr.pb.labquimica.backend.service.impl.HomeServiceImpl;
import br.edu.utfpr.pb.labquimica.backend.service.impl.SolicitacaoCadastroServiceImpl;
import br.edu.utfpr.pb.labquimica.backend.testes.modelRepository.FormularioRepositoryTests;
import br.edu.utfpr.pb.labquimica.backend.testes.modelRepository.ParticipacaoProgramaEnsinoRepositoryTests;
import br.edu.utfpr.pb.labquimica.backend.testes.modelRepository.SolicitacaoCadastroRepositoryTests;



@RunWith(SpringRunner.class)
@ActiveProfiles("test")


public class HomeServiceTests {

	@SpyBean
	HomeServiceImpl service;
	
	
	SolicitacaoCadastroServiceImpl SCSImpl;
	SolicitacaoCadastroRepositoryTests rep;
	SolicitacaoCadastroRepository rep2;
	
	FormularioRepositoryTests FormularioTests;
	
	FormularioRepository FormularioRepository;
	
	ParticipacaoProgramaEnsinoRepositoryTests PPPTests;
	
	public static IndicadorCabecalho criarIndicadorCabecalho() {
		
		
		return IndicadorCabecalho.builder()
				.qtdeFormularioEmAnalise(1)
				.qtdeFormularioEmEspera(0)
				.qtdeFormularioEncerrado(0)
				.build();
				
	}
	
	public static IndicadorSolicitacaoCadastro criarIndicadorSolicitacaoCadastro() {
		
		
		return IndicadorSolicitacaoCadastro.builder()
				.qtdeSolicitacao(2)
				.categoria("categoria solicitacao cadastro")
				.build();
	}
	
	public static IndicadorPessoaCategoria criarIndicadorPessoaCategoria() {
		
		return IndicadorPessoaCategoria.builder()
				.categoria("categoria pessoa categoria")
				.qtdePessoaCadastrada(3)
				.build();
		
	}
	

	
	
	
	@Test 
	public void retornaIndicadoresPelaData() {
		
//		IndicadorCabecalho indicadorCabecalho = new IndicadorCabecalho();
	//	indicadorCabecalho.setQtdeFormularioEmAnalise(2);
		
		
		
		
		SolicitacaoCadastro SC= rep.criarSolicitacaoCadastro();
		SC.setId(1l);
		
		rep2.save(SC);

		
		Indicadores indicadores = service.findDadosIndicadores(null, null);
		
		Assertions.assertThat(indicadores.getSolicitacaoCadastrosPendentes()).contains(SC);
		
		
		
	}
	
	
	
	@Test 
	public void retornaIndicadorCabecalho() {
		
		
		Formulario form1=   FormularioTests.criarFormulario();
		form1.setId(1l);
		form1.setDataSolicitacao(LocalDate.MIN);
		form1.setStatus(StatusFormulario.AMOSTRA_RECUSADA);
		
		ParticipacaoProgramaEnsino ppp=  PPPTests.criarParticipacaoProgramaEnsino();
		ppp.setDataTermino(LocalDate.MAX);
		form1.setParticipacaoPrograma(ppp);
		
		
		Formulario form2=   FormularioTests.criarFormulario();
		form2.setId(2l);
		form2.setDataSolicitacao(LocalDate.MIN);
		form2.setStatus(StatusFormulario.AGUARDANDO_AMOSTRA);
		ParticipacaoProgramaEnsino ppp2=  PPPTests.criarParticipacaoProgramaEnsino();
		ppp.setDataTermino(LocalDate.MAX);
		form2.setParticipacaoPrograma(ppp2);
		
		
		FormularioRepository.save(form1);
		FormularioRepository.save(form2);
		
		
	
		
		
		
		
		
		
	}
	
	
	
	
	
	
	
	
}
