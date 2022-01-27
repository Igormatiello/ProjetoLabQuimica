package br.edu.utfpr.pb.labquimica.backend.testes.service;

import java.time.LocalDate;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.stereotype.Repository;

import br.edu.utfpr.pb.labquimica.backend.enumerators.TipoPessoa;
import br.edu.utfpr.pb.labquimica.backend.model.Cidade;
import br.edu.utfpr.pb.labquimica.backend.model.Pessoa;
import br.edu.utfpr.pb.labquimica.backend.model.SolicitacaoCadastro;
import br.edu.utfpr.pb.labquimica.backend.repository.SolicitacaoCadastroRepository;

import br.edu.utfpr.pb.labquimica.backend.service.SolicitacaoCadastroService;
import br.edu.utfpr.pb.labquimica.backend.utils.ValidationMessages;
import br.edu.utfpr.pb.labquimica.backend.viewmodels.ResultadoOperacaoViewModel;


public class SolicitacaoCadastroServiceTests {

	
	

	@SpyBean
	SolicitacaoCadastroService service;
	
	@MockBean
	SolicitacaoCadastroRepository repository;
	
	@Test
	public void deveSalvarUmaSolicitacao() {
		
	//cenario
		
		Mockito.doNothing().when(service).saveWithValidation(Mockito.isNull());
		Cidade cidade = new Cidade();
		cidade.setNome("pato branco");
		cidade.setUf("PR");
		
		
		
		Pessoa pessoa = new Pessoa();
		pessoa.setDocumento("novo documento");
		pessoa.setBairro("norte");
		pessoa.setNome("igor matiello");
		
		SolicitacaoCadastro solicitacaoCadastro= SolicitacaoCadastro
				.builder()
				.senha("123")
				.tipoPessoa(TipoPessoa.Fisica)
				.cidade(cidade)
				.nome("igor")
				.documento("100")
				.cep("8986000")
				.endereco("rua dos marajos")
				.bairro("centro")
				.numero("877")
				.complemento("ed solymar")
				.nomeprojeto("analise quimica peixe")
				.telefone("898989")
				.celular("898989")
				.email("igor@email.com")
				.dataCriacao(LocalDate.now())
				.nomeInstituicao("utfpr")
				.cpfOrientador("1010")
				.nomeProgramaEnsino("nome")
				.ehProfessor(false)
				.dataTerminoProgramaEnsino(LocalDate.of(2022, 10, 4))
				.inscricaoEstadual("ie")
				.pessoa(pessoa)
				.build();
		
		Mockito.when(repository.save(Mockito.any(SolicitacaoCadastro.class))).thenReturn(solicitacaoCadastro);
		
		
		//ação
		ResultadoOperacaoViewModel<SolicitacaoCadastro> solicitacaoSalva = service.saveWithValidation(solicitacaoCadastro);
		
		
		//verificação
		Assertions.assertThat(solicitacaoSalva).isNotNull();
		Assertions.assertThat(solicitacaoSalva.isSucesso());
		
		
	}
		@Test
		public void deveLancarErroAoCadastrarUmaSolicitacaoComErroNoCpf() {
			
			//cenario
			SolicitacaoCadastro solicitacaoASalvar= SolicitacaoCadastro.builder().cpfOrientador("1234").build();
			
			
			Mockito.doThrow().when(service).saveWithValidation(solicitacaoASalvar);
			
			
			//execução e verificação
					
			Assertions.catchThrowableOfType(() ->service.saveWithValidation(solicitacaoASalvar), ValidationMessages.class);
			
			Mockito.verify(repository, Mockito.never()).save(solicitacaoASalvar);
			
			
			
		}
		
		
	}
	

