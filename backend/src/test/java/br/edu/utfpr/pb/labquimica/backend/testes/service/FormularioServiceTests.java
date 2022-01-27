package br.edu.utfpr.pb.labquimica.backend.testes.service;

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
import br.edu.utfpr.pb.labquimica.backend.model.Cidade;
import br.edu.utfpr.pb.labquimica.backend.model.Formulario;
import br.edu.utfpr.pb.labquimica.backend.model.Pessoa;
import br.edu.utfpr.pb.labquimica.backend.repository.EquipamentoRepository;
import br.edu.utfpr.pb.labquimica.backend.repository.FormularioRepository;
import br.edu.utfpr.pb.labquimica.backend.repository.HistoricoCreditoRepository;
import br.edu.utfpr.pb.labquimica.backend.repository.ParticipacaoProgramaEnsinoRepository;
import br.edu.utfpr.pb.labquimica.backend.repository.PessoaRepository;
import br.edu.utfpr.pb.labquimica.backend.service.impl.FormularioServiceImpl;
import br.edu.utfpr.pb.labquimica.backend.service.impl.HistoricoCreditoServiceImpl;
import br.edu.utfpr.pb.labquimica.backend.testes.modelRepository.EquipamentoRepositoryTests;
import br.edu.utfpr.pb.labquimica.backend.testes.modelRepository.FormularioRepositoryTests;
import br.edu.utfpr.pb.labquimica.backend.testes.modelRepository.ParticipacaoProgramaEnsinoRepositoryTests;
import br.edu.utfpr.pb.labquimica.backend.testes.modelRepository.PessoaRepositoryTests;

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
		
		List<Formulario> listaForms=new ArrayList<>();
		
		Formulario formulario= repositoryTest.criarFormulario();
		
		formulario.setStatus(StatusFormulario.FINALIZADO);
		
		formulario.setEquipamento(EquipamentoTest.criarEquipamento());
		
		formulario.setPessoa(PessoaTest.criarPessoa());
		
		listaForms.add(formulario);
		
		
	Mockito.when(service.findByStatusOrderByDataSolicitacaoDesc(StatusFormulario.FINALIZADO)).thenReturn(listaForms);
	
	
	repository.save(formulario);
	
	
	List<Formulario> lista = service.findByStatusOrderByDataSolicitacaoDesc(StatusFormulario.FINALIZADO);
	
	
	
	Assertions.assertThat(lista).isNotNull();
	
	Assertions.assertThat(lista).contains(formulario);
		
		
		
		
		
	}
	
	
	@Test
	public void deveRetornarZeroFormulariosPorStatus() {
		
		List<Formulario> listaForms=new ArrayList<>();
		
		Formulario formulario= repositoryTest.criarFormulario();
		
		formulario.setStatus(StatusFormulario.FINALIZADO);
		
		formulario.setEquipamento(EquipamentoTest.criarEquipamento());
		
		formulario.setPessoa(PessoaTest.criarPessoa());
		
		//listaForms.add(formulario);
		
		
	//Mockito.when(service.findByStatusOrderByDataSolicitacaoDesc(StatusFormulario.FINALIZADO)).thenReturn(listaForms);
	
	
	//repository.save(formulario);
	
	
	List<Formulario> lista = service.findByStatusOrderByDataSolicitacaoDesc(StatusFormulario.FINALIZADO);
	
	
	
	//Assertions.assertThat(lista).isNull();
	
	Assertions.assertThat(lista).isEmpty();
		
	
		
	}
	
	@Test
	public void deveListarOsFormulariosPorId() {
		
		List<Formulario> listaForms=new ArrayList<>();
		
		Formulario formulario= repositoryTest.criarFormulario();
		
		formulario.setStatus(StatusFormulario.FINALIZADO);
		
		formulario.setEquipamento(EquipamentoTest.criarEquipamento());
		
		Pessoa pessoa= PessoaTest.criarPessoa();
		
		pessoa.setId(1l);
		
		formulario.setPessoa(pessoa);
		
		
		listaForms.add(formulario);
		
		
	Mockito.when(service.findFormulariosByPessoaId(1l)).thenReturn(listaForms);
	
	
	repository.save(formulario);
	
	
	List<Formulario> lista = service.findFormulariosByPessoaId(1l);
	
	
	
	Assertions.assertThat(lista).isNotNull();
	
	Assertions.assertThat(lista).contains(formulario);
		
		
		
		
		
	}
	
	
	
	
	
}
