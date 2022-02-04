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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import br.edu.utfpr.pb.labquimica.backend.model.Cidade;
import br.edu.utfpr.pb.labquimica.backend.model.ParticipacaoProgramaEnsino;
import br.edu.utfpr.pb.labquimica.backend.model.PessoaInstituicao;
import br.edu.utfpr.pb.labquimica.backend.model.ProgramaEnsino;
import br.edu.utfpr.pb.labquimica.backend.repository.LancamentoFinanceiroRepository;
import br.edu.utfpr.pb.labquimica.backend.repository.ParticipacaoProgramaEnsinoRepository;
import br.edu.utfpr.pb.labquimica.backend.repository.PessoaInstituicaoRepository;
import br.edu.utfpr.pb.labquimica.backend.repository.ProgramaEnsinoRepository;
import br.edu.utfpr.pb.labquimica.backend.service.impl.ParticipacaoProgramaEnsinoServiceImpl;
import br.edu.utfpr.pb.labquimica.backend.testes.modelRepository.ParticipacaoProgramaEnsinoRepositoryTests;
import br.edu.utfpr.pb.labquimica.backend.testes.modelRepository.PessoalInstituicaoRepositoryTests;
import br.edu.utfpr.pb.labquimica.backend.testes.modelRepository.ProgramaEnsinoRepositoryTests;


@RunWith(SpringRunner.class)
@ActiveProfiles("test")


public class ParticipacaoProgramaEnsinoServiceTests {

	@SpyBean
	ParticipacaoProgramaEnsinoServiceImpl service;
	
	@MockBean
	ParticipacaoProgramaEnsinoRepository repository;
	
	
	
	
	@Autowired
	ProgramaEnsinoRepository PeRep;
	
	ProgramaEnsinoRepositoryTests PeTest;
	
	
	@Autowired
	PessoaInstituicaoRepository PiRep;
	
	
	
	PessoalInstituicaoRepositoryTests PiTest;
	
	public static ParticipacaoProgramaEnsino criarParticipacaoProgramaEnsino() {
		
		
		
		return ParticipacaoProgramaEnsino
				.builder()
				.programaEnsino(null)
				.participante(null)
				.orientador(null)
				.dataTermino(null)
				.ehAtivo(false)
				.build();
								
	}
	
	@Test
	public void deveRetornarProgramaEnsinoAtivoPelosIds() {
		
		
		ParticipacaoProgramaEnsino ppe= criarParticipacaoProgramaEnsino();
		
		ppe.setEhAtivo(true);
		ppe.setId(1l);
		ppe.setDataTermino(LocalDate.MAX);
		
		
		PessoaInstituicao piOrientador=PiTest.criarPessoaInstituicao();
		piOrientador.setId(12l);
		
		PiRep.save(piOrientador);
		
		ppe.setOrientador(piOrientador);
		
		PessoaInstituicao piParticipante=PiTest.criarPessoaInstituicao();
		
		PiRep.save(piParticipante);
		ppe.setParticipante(piParticipante);
		
		
		ProgramaEnsino pe= PeTest.criarProgramaEnsino();
		pe.setId(1000l);
		
		
		Mockito.when(repository.save(ppe)).thenReturn(ppe);
		repository.save(ppe);
		
		ppe.setProgramaEnsino(pe);
		
		ParticipacaoProgramaEnsino resultado= service.findByOrientadorIdAndProgramaEnsinoIdAndAndEhAtivoTrue(12l, 1l);
		
	
		Assertions.assertThat(resultado).isNotNull();
		
		Assertions.assertThat(resultado).isEqualTo(ppe);
		
		
		
	}
	
	
	@Test
	public void deveRetornarListaDeProgramaEnsinoAtivoPelosIdDoParticipante() {
		
		List<ParticipacaoProgramaEnsino> listaPPE=new ArrayList<>();
		
		
		ParticipacaoProgramaEnsino ppe=criarParticipacaoProgramaEnsino();
		
		ppe.setEhAtivo(true);
		ppe.setId(1l);
		ppe.setDataTermino(LocalDate.MAX);
		
		
		PessoaInstituicao piOrientador=PiTest.criarPessoaInstituicao();
		
		
		PiRep.save(piOrientador);
		
		ppe.setOrientador(piOrientador);
		
		PessoaInstituicao piParticipante=PiTest.criarPessoaInstituicao();
		piParticipante.setId(22l);
		
		PiRep.save(piParticipante);
		ppe.setParticipante(piParticipante);
		
		
		ProgramaEnsino pe= PeTest.criarProgramaEnsino();
		pe.setId(1000l);
		
		
		Mockito.when(repository.save(ppe)).thenReturn(ppe);
		repository.save(ppe);
		
		
		ppe.setProgramaEnsino(pe);
		
		listaPPE.add(ppe);
		
		
		List<ParticipacaoProgramaEnsino>    resultado= service.findParticipacaoPrograma(22l);
		
	
		Assertions.assertThat(resultado).isNotNull();
		
		Assertions.assertThat(resultado).hasSize(1);
		
		
		
	}
	
	@Test
	public void deveRetornarUmaListaVaziaDeProgramaEnsinoAtivo() {
		
		List<ParticipacaoProgramaEnsino> listaPPE=new ArrayList<>();
		
		
		ParticipacaoProgramaEnsino ppe= criarParticipacaoProgramaEnsino();
		
		ppe.setEhAtivo(true);
		ppe.setId(1l);
		ppe.setDataTermino(LocalDate.MAX);
		
		
		PessoaInstituicao piOrientador=PiTest.criarPessoaInstituicao();
		
		
		PiRep.save(piOrientador);
		
		ppe.setOrientador(piOrientador);
		
		PessoaInstituicao piParticipante=PiTest.criarPessoaInstituicao();
		piParticipante.setId(22l);
		
		PiRep.save(piParticipante);
		ppe.setParticipante(piParticipante);
		
		
		ProgramaEnsino pe= PeTest.criarProgramaEnsino();
		pe.setId(1000l);
		
		
		Mockito.when(repository.save(ppe)).thenReturn(ppe);
		repository.save(ppe);
		
		
		//ppe.setProgramaEnsino(pe);
		
		//listaPPE.add(ppe);
		
		
		List<ParticipacaoProgramaEnsino>    resultado= service.findParticipacaoPrograma(22l);
		
	
		Assertions.assertThat(resultado).isEmpty();
		
		
		
		
	}
}
