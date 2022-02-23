package br.edu.utfpr.pb.labquimica.backend.testes.service;

import java.time.LocalDate;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import br.edu.utfpr.pb.labquimica.backend.enumerators.TipoPessoa;
import br.edu.utfpr.pb.labquimica.backend.model.Pessoa;
import br.edu.utfpr.pb.labquimica.backend.model.SolicitacaoCadastro;
import br.edu.utfpr.pb.labquimica.backend.repository.InstituicaoRepository;
import br.edu.utfpr.pb.labquimica.backend.repository.PessoaRepository;
import br.edu.utfpr.pb.labquimica.backend.repository.ProgramaEnsinoRepository;
import br.edu.utfpr.pb.labquimica.backend.repository.SolicitacaoCadastroRepository;
import br.edu.utfpr.pb.labquimica.backend.service.EmailService;
import br.edu.utfpr.pb.labquimica.backend.service.SolicitacaoCadastroService;
import br.edu.utfpr.pb.labquimica.backend.service.impl.PapelServiceImpl;
import br.edu.utfpr.pb.labquimica.backend.service.impl.ParticipacaoProgramaEnsinoServiceImpl;
import br.edu.utfpr.pb.labquimica.backend.service.impl.PessoaInstituicaoServiceImpl;
import br.edu.utfpr.pb.labquimica.backend.service.impl.PessoaServiceImpl;
import br.edu.utfpr.pb.labquimica.backend.service.impl.SolicitacaoCadastroServiceImpl;
import br.edu.utfpr.pb.labquimica.backend.service.impl.UsuarioServiceImpl;
import br.edu.utfpr.pb.labquimica.backend.viewmodels.ResultadoOperacaoViewModel;
import br.edu.utfpr.pb.labquimica.backend.viewmodels.SolicitacaoCadastroViewModel;
import br.edu.utfpr.pb.labquimica.backend.testes.modelRepository.CriarObjetos;
import br.edu.utfpr.pb.labquimica.backend.testes.modelRepository.PessoaRepositoryTests;
import br.edu.utfpr.pb.labquimica.backend.testes.modelRepository.PessoalInstituicaoRepositoryTests;
import br.edu.utfpr.pb.labquimica.backend.testes.modelRepository.SolicitacaoCadastroRepositoryTests;
import br.edu.utfpr.pb.labquimica.backend.utils.ValidationMessages;

@RunWith(SpringRunner.class)
@ActiveProfiles("test")

public class PessoaServiceTests {

	@SpyBean
	PessoaServiceImpl service;

	@MockBean
	PessoaRepository repository;

	// @Test
	// public void salvaEValidaUmaPessoa() {

	// SolicitacaoCadastro SC=
	// SolicitacaoCadastroRepositoryTests.criarSolicitacaoCadastro();

	// SolicitacaoCadastroViewModel len=

	// ResultadoOperacaoViewModel<> var = service.saveValidarCadastro(SC);

//	}

	@Test
	public void deveValidarOCadastro() {

		SolicitacaoCadastroViewModel sc = new SolicitacaoCadastroViewModel();
		sc.setBairro("bairro");
		sc.setCelular("celular");
		sc.setCep("cep");
		sc.setCidade(CriarObjetos.criarCidade());
		sc.setComplemento("complemento");
		sc.setCpfOrientador("cpf");
		sc.setDataCriacao(LocalDate.now());
		sc.setDataTerminoPrograma(LocalDate.of(12, 12, 2025));
		sc.setDocumento("doc");
		sc.setEhProfessor(false);
		sc.setEmail("email");
		sc.setEndereco("end");
		sc.setId(1l);
		sc.setInscricaoEstadual("inscricao");
		sc.setInstituicao(CriarObjetos.criarInstituicao());
		sc.setNome("nome");
		sc.setNomeInstituicao("nome instituicao");
		sc.setNomeProgramaEnsino("programa ensino");
		sc.setNomeprojeto("nome projeto");
		sc.setNumero("nomero");
		sc.setOrientador(CriarObjetos.criarPessoaInstituicao());
		sc.setProgramaEnsino(CriarObjetos.criarProgramaEnsino());
		sc.setTelefone("fone");
		sc.setTipoPessoa(TipoPessoa.Fisica);

		ResultadoOperacaoViewModel<Pessoa> resultado = service.saveValidarCadastro(sc);

		Assertions.assertThat(resultado).isNotNull();

	}

	@Test
	public void deveValidarSolicitacao() {

		SolicitacaoCadastro sc = CriarObjetos.criarSolicitacaoCadastro();
		sc.setId(1l);

		SolicitacaoCadastroViewModel resultado = service.getDadosValidacaoCadastro(1l);

		Assertions.assertThat(repository.findById(1l)).isNotNull();
		Assertions.assertThat(resultado).isNotNull();

	}

	@Test
	public void deveEncontrarPessoaPeloDocumento() {

		Pessoa pessoa = CriarObjetos.criarPessoa();
		pessoa.setDocumento("12");
		pessoa.setTipoPessoa(TipoPessoa.Fisica);

		repository.save(pessoa);

		ResultadoOperacaoViewModel resultado = service.findCpfCnpjCadastrado(TipoPessoa.Fisica, "12");

		Assertions.assertThat(resultado).isNotNull();

	}

	@Test
	public void naoDeveEncontrarPessoaPeloDocumento() {

		Pessoa pessoa = CriarObjetos.criarPessoa();
		pessoa.setDocumento("12");
		pessoa.setTipoPessoa(TipoPessoa.Fisica);

		repository.save(pessoa);

		ResultadoOperacaoViewModel resultado = service.findCpfCnpjCadastrado(TipoPessoa.Juridica, "11");

		Assertions.assertThat(resultado.returningFailMessage(ValidationMessages.CpfNaoExiste));

	}

	@Test
	public void validaCadastro() {

		SolicitacaoCadastro solicitacaoCadastro = CriarObjetos.criarSolicitacaoCadastro();
		solicitacaoCadastro.setId(1l);
		solicitacaoCadastro.setNomeInstituicao("ufpr");
		solicitacaoCadastro.setNomeProgramaEnsino("programa ensino");
		solicitacaoCadastro.setEhProfessor(true);

		SolicitacaoCadastroViewModel resultado = service.getDadosValidacaoCadastro(1l);

		Assertions.assertThat(resultado.getNomeInstituicao()).isEqualTo("ufpr");
		Assertions.assertThat(resultado.getNomeProgramaEnsino()).isEqualTo("programa ensino");

	}

	@Test
	public void deveLancarErroNoDocumentoAoValidaCadastro() {

		SolicitacaoCadastro solicitacaoCadastro = CriarObjetos.criarSolicitacaoCadastro();
		Long id = solicitacaoCadastro.getId();
		solicitacaoCadastro.setTipoPessoa(TipoPessoa.Fisica);
		solicitacaoCadastro.setDocumento("");

		SolicitacaoCadastroViewModel resultado = service.getDadosValidacaoCadastro(id);

		Mockito.doThrow(new RuntimeException(ValidationMessages.FormatoCpfInvalido)).when(service)
				.getDadosValidacaoCadastro(id);

		Throwable exception = Assertions.catchThrowable(() -> service.getDadosValidacaoCadastro(id));
		Assertions.assertThat(exception).isInstanceOf(ValidationMessages.class)
				.hasMessage(ValidationMessages.FormatoCpfInvalido);

	}

}
