package br.edu.utfpr.pb.labquimica.backend.testes.service;

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

import br.edu.utfpr.pb.labquimica.backend.enumerators.StatusFormulario;
import br.edu.utfpr.pb.labquimica.backend.enumerators.TipoPessoa;
import br.edu.utfpr.pb.labquimica.backend.model.Cidade;
import br.edu.utfpr.pb.labquimica.backend.model.Formulario;
import br.edu.utfpr.pb.labquimica.backend.model.IndicadorFormularioByDay;
import br.edu.utfpr.pb.labquimica.backend.model.ParticipacaoProgramaEnsino;
import br.edu.utfpr.pb.labquimica.backend.model.Pessoa;
import br.edu.utfpr.pb.labquimica.backend.repository.EquipamentoRepository;
import br.edu.utfpr.pb.labquimica.backend.repository.FormularioRepository;
import br.edu.utfpr.pb.labquimica.backend.repository.HistoricoCreditoRepository;
import br.edu.utfpr.pb.labquimica.backend.repository.ParticipacaoProgramaEnsinoRepository;
import br.edu.utfpr.pb.labquimica.backend.repository.PessoaRepository;
import br.edu.utfpr.pb.labquimica.backend.service.impl.FormularioServiceImpl;
import br.edu.utfpr.pb.labquimica.backend.service.impl.HistoricoCreditoServiceImpl;
import br.edu.utfpr.pb.labquimica.backend.testes.modelRepository.CriarObjetos;
import br.edu.utfpr.pb.labquimica.backend.testes.modelRepository.EquipamentoRepositoryTests;
import br.edu.utfpr.pb.labquimica.backend.testes.modelRepository.FormularioRepositoryTests;
import br.edu.utfpr.pb.labquimica.backend.testes.modelRepository.ParticipacaoProgramaEnsinoRepositoryTests;
import br.edu.utfpr.pb.labquimica.backend.testes.modelRepository.PessoaRepositoryTests;
import br.edu.utfpr.pb.labquimica.backend.utils.ValidationMessages;

@RunWith(SpringRunner.class)
@ActiveProfiles("test")

public class FormularioServiceTests {

	@SpyBean
	FormularioServiceImpl service;

	@MockBean
	FormularioRepository repository;

	FormularioRepositoryTests repositoryTest;

	PessoaRepositoryTests PessoaTest;

	PessoaRepository PessoaRepository;

	ParticipacaoProgramaEnsinoRepositoryTests ParticipacaoTest;

	ParticipacaoProgramaEnsinoRepository ParticipacaoRepository;

	EquipamentoRepositoryTests EquipamentoTest;

	EquipamentoRepository EquipamentoRepository;

	@Test
	public void deveListarOsFormulariosPorStatus() {

		Formulario forms = CriarObjetos.criarFormulario();

		repository.save(forms);

		List<Formulario> listaForms = new ArrayList<>();

		listaForms.add(forms);

		StatusFormulario status = forms.getStatus();

		Mockito.when(service.findByStatusOrderByDataSolicitacaoDesc(status)).thenReturn(listaForms);

		List<Formulario> lista = service.findByStatusOrderByDataSolicitacaoDesc(status);

		Assertions.assertThat(lista).isNotNull();

		Assertions.assertThat(lista).contains(forms);

	}

	@Test
	public void deveRetornarZeroFormulariosPorStatus() {

		Formulario forms = CriarObjetos.criarFormulario();

		repository.save(forms);

		List<Formulario> listaForms = new ArrayList<>();

		StatusFormulario status = forms.getStatus();

		Mockito.when(service.findByStatusOrderByDataSolicitacaoDesc(status)).thenReturn(listaForms);

		List<Formulario> lista = service.findByStatusOrderByDataSolicitacaoDesc(status);

		Assertions.assertThat(lista).isNull();

		Assertions.assertThat(lista).isEmpty();

	}

	@Test
	public void deveListarOsFormulariosPorId() {

		Formulario forms = CriarObjetos.criarFormulario();

		Long id = forms.getPessoa().getId();

		repository.save(forms);

		List<Formulario> listaForms = new ArrayList<>();

		listaForms.add(forms);

		Mockito.when(service.findFormulariosByPessoaId(id)).thenReturn(listaForms);

		List<Formulario> lista = service.findFormulariosByPessoaId(id);

		Assertions.assertThat(lista).isNotNull();

		Assertions.assertThat(lista).contains(forms);

	}

	@Test
	public void naoDeveEncontrarFormulariosPorId() {

		Formulario forms = CriarObjetos.criarFormulario();

		Long id = forms.getPessoa().getId();

		repository.save(forms);

		List<Formulario> listaForms = new ArrayList<>();

		Mockito.when(service.findFormulariosByPessoaId(id)).thenReturn(listaForms);

		List<Formulario> lista = service.findFormulariosByPessoaId(id);

		Assertions.assertThat(lista).isNull();

		Assertions.assertThat(lista).isEmpty();

	}

	@Test
	public void deveListarOsFormulariosDePessoasJuridicasPorID() {

		Formulario forms = CriarObjetos.criarFormulario();

		Long id = forms.getPessoa().getId();

		forms.getPessoa().setTipoPessoa(TipoPessoa.Juridica);

		repository.save(forms);

		List<Formulario> listaForms = new ArrayList<>();

		listaForms.add(forms);

		Mockito.when(service.buscaFormulariosPessoaJuridica(id)).thenReturn(listaForms);

		List<Formulario> lista = service.buscaFormulariosPessoaJuridica(id);

		Assertions.assertThat(lista).isNotNull();

		Assertions.assertThat(lista).contains(forms);

	}

	@Test
	public void naoDeveEncontrarFormulariosDePessoasJuridicasPorID() {

		Formulario forms = CriarObjetos.criarFormulario();

		Long id = forms.getPessoa().getId();

		forms.getPessoa().setTipoPessoa(TipoPessoa.Fisica);

		repository.save(forms);

		List<Formulario> listaForms = new ArrayList<>();

		listaForms.add(forms);

		Mockito.when(service.buscaFormulariosPessoaJuridica(id)).thenReturn(listaForms);

		List<Formulario> lista = service.buscaFormulariosPessoaJuridica(id);

		Assertions.assertThat(lista).isNull();

		Assertions.assertThat(lista).isEmpty();

	}

	@Test
	public void deveListarOsFormulariosPelaData() {

		Formulario forms = CriarObjetos.criarFormulario();

		LocalDate data = forms.getDataSolicitacao();

		repository.save(forms);

		List<Formulario> listaForms = new ArrayList<>();

		listaForms.add(forms);

		Mockito.when(service.findAllByDataSolicitacaoBetween(data, LocalDate.MAX)).thenReturn(listaForms);

		List<Formulario> lista = service.findAllByDataSolicitacaoBetween(data, LocalDate.MAX);

		Assertions.assertThat(lista).isNotNull();

		Assertions.assertThat(lista).contains(forms);

	}

	@Test
	public void deveLancarErroAoMandarPessoaNula() {

		Formulario forms = CriarObjetos.criarFormulario();

		forms.setPessoa(null);

		Long id = forms.getId();

		repository.save(forms);

		Mockito.doThrow(new RuntimeException(ValidationMessages.PessoaNaoPodeSerVazio)).when(service)
				.findFormulariosByPessoaId(id);

		Throwable exception = Assertions.catchThrowable(() -> service.findFormulariosByPessoaId(id));
		Assertions.assertThat(exception).isInstanceOf(ValidationMessages.class)
				.hasMessage(ValidationMessages.PessoaNaoPodeSerVazio);

	}

	@Test
	public void deveLancarErroAoMandarFormularioNulo() {

		Formulario forms = CriarObjetos.criarFormulario();

		forms.setPessoa(null);

		Long id = forms.getId();

		repository.save(forms);

		Mockito.doThrow(new RuntimeException(ValidationMessages.PessoaNaoPodeSerVazio)).when(service)
				.findFormulariosByPessoaId(id);

		Throwable exception = Assertions.catchThrowable(() -> service.findFormulariosByPessoaId(id));
		Assertions.assertThat(exception).isInstanceOf(ValidationMessages.class)
				.hasMessage(ValidationMessages.PessoaNaoPodeSerVazio);

	}

	@Test
	public void deveLancarErroAoMandarStatusNulo() {

		Formulario forms = CriarObjetos.criarFormulario();

		forms.setStatus(null);

		Long id = forms.getId();

		repository.save(forms);

		Mockito.doThrow(new RuntimeException(ValidationMessages.StatusFormularioNaoPodeSerVazio)).when(service)
				.findFormulariosByPessoaId(id);

		Throwable exception = Assertions.catchThrowable(() -> service.findFormulariosByPessoaId(id));
		Assertions.assertThat(exception).isInstanceOf(ValidationMessages.class)
				.hasMessage(ValidationMessages.StatusFormularioNaoPodeSerVazio);

	}

}
