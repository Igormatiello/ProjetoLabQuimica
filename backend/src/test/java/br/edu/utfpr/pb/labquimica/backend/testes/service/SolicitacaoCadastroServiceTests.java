package br.edu.utfpr.pb.labquimica.backend.testes.service;

import java.time.LocalDate;
import java.util.List;

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
import br.edu.utfpr.pb.labquimica.backend.repository.PessoaRepository;
import br.edu.utfpr.pb.labquimica.backend.repository.SolicitacaoCadastroRepository;

import br.edu.utfpr.pb.labquimica.backend.service.SolicitacaoCadastroService;
import br.edu.utfpr.pb.labquimica.backend.testes.modelRepository.CriarObjetos;
import br.edu.utfpr.pb.labquimica.backend.utils.ValidationMessages;
import br.edu.utfpr.pb.labquimica.backend.viewmodels.ResultadoOperacaoViewModel;

public class SolicitacaoCadastroServiceTests {

	@SpyBean
	SolicitacaoCadastroService service;

	@MockBean
	SolicitacaoCadastroRepository repository;

	PessoaRepository pessoaRepository;

	@Test
	public void deveRetornarErroAoTentarSalvarComCpfInvalido() {

		Cidade cidade = CriarObjetos.criarCidade();

		Pessoa pessoa = CriarObjetos.criarPessoa();

		SolicitacaoCadastro solicitacaoCadastro = SolicitacaoCadastro.builder().senha("123")
				.tipoPessoa(TipoPessoa.Fisica).cidade(cidade).nome("igor").documento("100").cep("8986000")
				.endereco("rua dos marajos").bairro("centro").numero("877").complemento("ed solymar")
				.nomeprojeto("analise quimica peixe").telefone("898989").celular("898989").email("igor@email.com")
				.dataCriacao(LocalDate.now()).nomeInstituicao("utfpr").cpfOrientador("1010").nomeProgramaEnsino("nome")
				.ehProfessor(false).dataTerminoProgramaEnsino(LocalDate.of(2022, 10, 4)).inscricaoEstadual("ie")
				.pessoa(pessoa).build();

		Mockito.doThrow(new RuntimeException(ValidationMessages.FormatoCpfInvalido)).when(service)
				.saveWithValidation(solicitacaoCadastro);

		Throwable exception = Assertions.catchThrowable(() -> service.saveWithValidation(solicitacaoCadastro));
		Assertions.assertThat(exception).isInstanceOf(ValidationMessages.class)
				.hasMessage(ValidationMessages.FormatoCpfInvalido);

	}

	@Test
	public void deveRetornarErroAoTentarSalvarComCnpjInvalido() {

		Cidade cidade = CriarObjetos.criarCidade();

		Pessoa pessoa = CriarObjetos.criarPessoa();

		SolicitacaoCadastro solicitacaoCadastro = SolicitacaoCadastro.builder().senha("123")
				.tipoPessoa(TipoPessoa.Juridica).cidade(cidade).nome("igor").documento("100").cep("8986000")
				.endereco("rua dos marajos").bairro("centro").numero("877").complemento("ed solymar")
				.nomeprojeto("analise quimica peixe").telefone("898989").celular("898989").email("igor@email.com")
				.dataCriacao(LocalDate.now()).nomeInstituicao("utfpr").cpfOrientador("1010").nomeProgramaEnsino("nome")
				.ehProfessor(false).dataTerminoProgramaEnsino(LocalDate.of(2022, 10, 4)).inscricaoEstadual("ie")
				.pessoa(pessoa).build();

		Mockito.doThrow(new RuntimeException(ValidationMessages.FormatoCnpjInvalido)).when(service)
				.saveWithValidation(solicitacaoCadastro);

		Throwable exception = Assertions.catchThrowable(() -> service.saveWithValidation(solicitacaoCadastro));
		Assertions.assertThat(exception).isInstanceOf(ValidationMessages.class)
				.hasMessage(ValidationMessages.FormatoCnpjInvalido);

	}

	@Test
	public void deveRetornarErroAoTentarSalvarPessoaJaSalva() {

		Cidade cidade = CriarObjetos.criarCidade();

		Pessoa pessoaAlternativa = CriarObjetos.criarPessoa();
		pessoaRepository.save(pessoaAlternativa);

		Pessoa pessoa = CriarObjetos.criarPessoa();

		SolicitacaoCadastro solicitacaoCadastro = SolicitacaoCadastro.builder().senha("123")
				.tipoPessoa(TipoPessoa.Juridica).cidade(cidade).nome("igor").documento("100").cep("8986000")
				.endereco("rua dos marajos").bairro("centro").numero("877").complemento("ed solymar")
				.nomeprojeto("analise quimica peixe").telefone("898989").celular("898989").email("igor@email.com")
				.dataCriacao(LocalDate.now()).nomeInstituicao("utfpr").cpfOrientador("1010").nomeProgramaEnsino("nome")
				.ehProfessor(false).dataTerminoProgramaEnsino(LocalDate.of(2022, 10, 4)).inscricaoEstadual("ie")
				.pessoa(pessoa).build();

		Mockito.doThrow(new RuntimeException(ValidationMessages.PessoaJaExiste)).when(service)
				.saveWithValidation(solicitacaoCadastro);

		Throwable exception = Assertions.catchThrowable(() -> service.saveWithValidation(solicitacaoCadastro));
		Assertions.assertThat(exception).isInstanceOf(ValidationMessages.class)
				.hasMessage(ValidationMessages.PessoaJaExiste);

	}

	@Test
	public void deveRetornarErroAoTentarSalvarSemProfessorCadastrado() {

		Cidade cidade = CriarObjetos.criarCidade();

		Pessoa pessoa = CriarObjetos.criarPessoa();
		pessoa.setTipoPessoa(TipoPessoa.Juridica);

		SolicitacaoCadastro solicitacaoCadastro = SolicitacaoCadastro.builder().senha("123")
				.tipoPessoa(TipoPessoa.Fisica).cidade(cidade).nome("igor").documento("100").cep("8986000")
				.endereco("rua dos marajos").bairro("centro").numero("877").complemento("ed solymar")
				.nomeprojeto("analise quimica peixe").telefone("898989").celular("898989").email("igor@email.com")
				.dataCriacao(LocalDate.now()).nomeInstituicao("utfpr").cpfOrientador("1010").nomeProgramaEnsino("nome")
				.ehProfessor(false).dataTerminoProgramaEnsino(LocalDate.of(2022, 10, 4)).inscricaoEstadual("ie")
				.pessoa(pessoa).build();

		Mockito.doThrow(new RuntimeException(ValidationMessages.ProfessorNaoCadastrado)).when(service)
				.saveWithValidation(solicitacaoCadastro);

		Throwable exception = Assertions.catchThrowable(() -> service.saveWithValidation(solicitacaoCadastro));
		Assertions.assertThat(exception).isInstanceOf(ValidationMessages.class)
				.hasMessage(ValidationMessages.ProfessorNaoCadastrado);

	}

	@Test
	public void deveSalvarEValidarUmaSolicitacaoCadastro() {

		Cidade cidade = CriarObjetos.criarCidade();

		Pessoa pessoa = CriarObjetos.criarPessoa();

		SolicitacaoCadastro solicitacaoCadastro = SolicitacaoCadastro.builder().senha("123")
				.tipoPessoa(TipoPessoa.Fisica).cidade(cidade).nome("igor").documento("12345678910").cep("8986000")
				.endereco("rua dos marajos").bairro("centro").numero("877").complemento("ed solymar")
				.nomeprojeto("analise quimica peixe").telefone("898989").celular("898989").email("igor@email.com")
				.dataCriacao(LocalDate.now()).nomeInstituicao("utfpr").cpfOrientador("1010").nomeProgramaEnsino("nome")
				.ehProfessor(true).dataTerminoProgramaEnsino(LocalDate.of(2022, 10, 4)).inscricaoEstadual("ie")
				.pessoa(pessoa).build();

		ResultadoOperacaoViewModel<SolicitacaoCadastro> resultado = service.saveWithValidation(solicitacaoCadastro);
		Assertions.assertThat(resultado.returningSuccess());
		Assertions.assertThat(resultado).isEqualTo(solicitacaoCadastro);

	}

	@Test
	public void deveProcurarSolicitacoesAbertas() {

		Cidade cidade1 = CriarObjetos.criarCidade();
		Cidade cidade2 = CriarObjetos.criarCidade();

		Pessoa pessoa1 = CriarObjetos.criarPessoa();
		Pessoa pessoa2 = CriarObjetos.criarPessoa();

		SolicitacaoCadastro solicitacaoCadastro1 = SolicitacaoCadastro.builder().senha("123")
				.tipoPessoa(TipoPessoa.Fisica).cidade(cidade1).nome("igor").documento("12345678910").cep("8986000")
				.endereco("rua dos marajos").bairro("centro").numero("877").complemento("ed solymar")
				.nomeprojeto("analise quimica peixe").telefone("898989").celular("898989").email("igor@email.com")
				.dataCriacao(LocalDate.now()).nomeInstituicao("utfpr").cpfOrientador("1010").nomeProgramaEnsino("nome")
				.ehProfessor(true).dataTerminoProgramaEnsino(LocalDate.of(2022, 10, 4)).inscricaoEstadual("ie")
				.pessoa(pessoa1).build();

		SolicitacaoCadastro solicitacaoCadastro2 = SolicitacaoCadastro.builder().senha("123")
				.tipoPessoa(TipoPessoa.Fisica).cidade(cidade2).nome("igor").documento("12345678910").cep("8986000")
				.endereco("rua dos marajos").bairro("centro").numero("877").complemento("ed solymar")
				.nomeprojeto("analise quimica peixe").telefone("898989").celular("898989").email("igor@email.com")
				.dataCriacao(LocalDate.now()).nomeInstituicao("utfpr").cpfOrientador("1010").nomeProgramaEnsino("nome")
				.ehProfessor(true).dataTerminoProgramaEnsino(LocalDate.of(2022, 10, 4)).inscricaoEstadual("ie")
				.pessoa(pessoa2).build();

		List<SolicitacaoCadastro> resultado = service.findSolicitacoesAbertas();

		Assertions.assertThat(resultado).contains(solicitacaoCadastro1, solicitacaoCadastro2);

		Assertions.assertThat(resultado).isNotNull();

	}

	@Test
	public void deveProcurarSolicitacoesDeUmPeriodo() {

		Cidade cidade1 = CriarObjetos.criarCidade();
		Cidade cidade2 = CriarObjetos.criarCidade();
		Cidade cidade3 = CriarObjetos.criarCidade();

		Pessoa pessoa1 = CriarObjetos.criarPessoa();
		Pessoa pessoa2 = CriarObjetos.criarPessoa();
		Pessoa pessoa3 = CriarObjetos.criarPessoa();

		SolicitacaoCadastro solicitacaoCadastro1 = SolicitacaoCadastro.builder().senha("123")
				.tipoPessoa(TipoPessoa.Fisica).cidade(cidade1).nome("igor").documento("12345678910").cep("8986000")
				.endereco("rua dos marajos").bairro("centro").numero("877").complemento("ed solymar")
				.nomeprojeto("analise quimica peixe").telefone("898989").celular("898989").email("igor@email.com")
				.dataCriacao(LocalDate.of(2021, 1, 1)).nomeInstituicao("utfpr").cpfOrientador("1010")
				.nomeProgramaEnsino("nome").ehProfessor(true).dataTerminoProgramaEnsino(LocalDate.of(2021, 10, 4))
				.inscricaoEstadual("ie").pessoa(pessoa1).build();

		SolicitacaoCadastro solicitacaoCadastro2 = SolicitacaoCadastro.builder().senha("123")
				.tipoPessoa(TipoPessoa.Fisica).cidade(cidade2).nome("igor").documento("12345678910").cep("8986000")
				.endereco("rua dos marajos").bairro("centro").numero("877").complemento("ed solymar")
				.nomeprojeto("analise quimica peixe").telefone("898989").celular("898989").email("igor@email.com")
				.dataCriacao(LocalDate.of(2021, 1, 2)).nomeInstituicao("utfpr").cpfOrientador("1010")
				.nomeProgramaEnsino("nome").ehProfessor(true).dataTerminoProgramaEnsino(LocalDate.of(2025, 10, 4))
				.inscricaoEstadual("ie").pessoa(pessoa2).build();

		SolicitacaoCadastro solicitacaoCadastro3 = SolicitacaoCadastro.builder().senha("123")
				.tipoPessoa(TipoPessoa.Fisica).cidade(cidade3).nome("igor").documento("12345678910").cep("8986000")
				.endereco("rua dos marajos").bairro("centro").numero("877").complemento("ed solymar")
				.nomeprojeto("analise quimica peixe").telefone("898989").celular("898989").email("igor@email.com")
				.dataCriacao(LocalDate.of(2018, 1, 1)).nomeInstituicao("utfpr").cpfOrientador("1010")
				.nomeProgramaEnsino("nome").ehProfessor(true).dataTerminoProgramaEnsino(LocalDate.of(2027, 10, 4))
				.inscricaoEstadual("ie").pessoa(pessoa3).build();

		List<SolicitacaoCadastro> resultado = service.findAllByDataCriacaoBetween(LocalDate.of(2020, 1, 1),
				LocalDate.of(2026, 1, 1));

		Assertions.assertThat(resultado).contains(solicitacaoCadastro1, solicitacaoCadastro2);

		Assertions.assertThat(resultado).isNotNull();

	}

}
