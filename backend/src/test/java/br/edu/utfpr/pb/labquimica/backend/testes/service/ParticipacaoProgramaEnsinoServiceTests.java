package br.edu.utfpr.pb.labquimica.backend.testes.service;

import static org.hamcrest.CoreMatchers.any;
import static org.hamcrest.CoreMatchers.anything;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import br.edu.utfpr.pb.labquimica.backend.model.ParticipacaoProgramaEnsino;
import br.edu.utfpr.pb.labquimica.backend.model.Pessoa;
import br.edu.utfpr.pb.labquimica.backend.model.PessoaInstituicao;
import br.edu.utfpr.pb.labquimica.backend.model.ProgramaEnsino;
import br.edu.utfpr.pb.labquimica.backend.repository.ParticipacaoProgramaEnsinoRepository;
import br.edu.utfpr.pb.labquimica.backend.service.impl.ParticipacaoProgramaEnsinoServiceImpl;
import br.edu.utfpr.pb.labquimica.backend.testes.modelRepository.CriarObjetos;
import br.edu.utfpr.pb.labquimica.backend.utils.ValidationMessages;

@RunWith(SpringRunner.class)
@ActiveProfiles("test")

public class ParticipacaoProgramaEnsinoServiceTests {

	@SpyBean
	ParticipacaoProgramaEnsinoServiceImpl service;

	@MockBean
	ParticipacaoProgramaEnsinoRepository repository;

	@Test
	public void deveRetornarProgramaEnsinoAtivoPelosIds() {

		ParticipacaoProgramaEnsino participacaoProgramaEnsino = CriarObjetos.criarParticipacaoProgramaEnsino();

		PessoaInstituicao pessoaInstituicao = CriarObjetos.criarPessoaInstituicao();
		pessoaInstituicao.setEhAtivo(true);

		Pessoa pessoa = CriarObjetos.criarPessoa();
		Long idPessoa = pessoa.getId();

		pessoaInstituicao.setPessoa(pessoa);
		participacaoProgramaEnsino.setOrientador(pessoaInstituicao);

		Long idPrograma = participacaoProgramaEnsino.getId();

		repository.save(participacaoProgramaEnsino);

		Mockito.when(service.findByOrientadorIdAndProgramaEnsinoIdAndAndEhAtivoTrue(idPessoa, idPrograma))
				.thenReturn(participacaoProgramaEnsino);

		ParticipacaoProgramaEnsino resultado = service.findByOrientadorIdAndProgramaEnsinoIdAndAndEhAtivoTrue(idPessoa,
				idPrograma);

		Assertions.assertThat(resultado).isNotNull();

		Assertions.assertThat(resultado).isEqualTo(participacaoProgramaEnsino);

	}

	@Test
	public void deveLancarErroAoProcurarOrientadorVazio() {

		ParticipacaoProgramaEnsino participacaoProgramaEnsino = CriarObjetos.criarParticipacaoProgramaEnsino();

		PessoaInstituicao pessoaInstituicao = CriarObjetos.criarPessoaInstituicao();
		pessoaInstituicao.setEhAtivo(true);

		Pessoa pessoa = CriarObjetos.criarPessoa();
		Long idPessoa = pessoa.getId();

		pessoaInstituicao.setPessoa(pessoa);
		participacaoProgramaEnsino.setOrientador(pessoaInstituicao);

		Long idPrograma = participacaoProgramaEnsino.getId();

		repository.save(participacaoProgramaEnsino);

		Mockito.doThrow(new RuntimeException(ValidationMessages.OrientadorNaoPodeSerVazio)).when(service)
				.findByOrientadorIdAndProgramaEnsinoIdAndAndEhAtivoTrue(null, idPrograma);

		Throwable exception = Assertions
				.catchThrowable(() -> service.findByOrientadorIdAndProgramaEnsinoIdAndAndEhAtivoTrue(null, idPrograma));
		Assertions.assertThat(exception).isInstanceOf(ValidationMessages.class)
				.hasMessage(ValidationMessages.OrientadorNaoPodeSerVazio);

	}

	@Test
	public void deveRetornarListaDeProgramaEnsinoAtivoPelosIdDoParticipante() {

		List<ParticipacaoProgramaEnsino> listaPPE = new ArrayList<>();

		ParticipacaoProgramaEnsino ppe = CriarObjetos.criarParticipacaoProgramaEnsino();

		ppe.setEhAtivo(true);
		ppe.setId(1l);
		ppe.setDataTermino(LocalDate.MAX);

		PessoaInstituicao piOrientador = CriarObjetos.criarPessoaInstituicao();

		ppe.setOrientador(piOrientador);

		PessoaInstituicao piParticipante = CriarObjetos.criarPessoaInstituicao();
		piParticipante.setId(22l);

		ppe.setParticipante(piParticipante);

		ProgramaEnsino pe = CriarObjetos.criarProgramaEnsino();
		pe.setId(1000l);

		Mockito.when(repository.save(ppe)).thenReturn(ppe);
		repository.save(ppe);

		ppe.setProgramaEnsino(pe);

		listaPPE.add(ppe);

		List<ParticipacaoProgramaEnsino> resultado = service.findParticipacaoPrograma(22l);

		Assertions.assertThat(resultado).isNotNull();

		Assertions.assertThat(resultado).hasSize(1);

	}

	@Test
	public void deveRetornarUmaListaVaziaDeProgramaEnsinoAtivo() {

		List<ParticipacaoProgramaEnsino> listaPPE = new ArrayList<>();

		ParticipacaoProgramaEnsino ppe = CriarObjetos.criarParticipacaoProgramaEnsino();

		ppe.setEhAtivo(true);
		ppe.setId(1l);
		ppe.setDataTermino(LocalDate.MAX);

		PessoaInstituicao piOrientador = CriarObjetos.criarPessoaInstituicao();

		ppe.setOrientador(piOrientador);

		PessoaInstituicao piParticipante = CriarObjetos.criarPessoaInstituicao();
		piParticipante.setId(22l);

		ppe.setParticipante(piParticipante);

		ProgramaEnsino pe = CriarObjetos.criarProgramaEnsino();
		pe.setId(1000l);

		Mockito.when(repository.save(ppe)).thenReturn(ppe);
		repository.save(ppe);

		// ppe.setProgramaEnsino(pe);

		// listaPPE.add(ppe);

		List<ParticipacaoProgramaEnsino> resultado = service.findParticipacaoPrograma(22l);

		Assertions.assertThat(resultado).isEmpty();

	}
}
