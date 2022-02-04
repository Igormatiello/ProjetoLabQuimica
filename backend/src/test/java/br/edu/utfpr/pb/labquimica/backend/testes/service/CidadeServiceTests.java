package br.edu.utfpr.pb.labquimica.backend.testes.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import br.edu.utfpr.pb.labquimica.backend.model.Cidade;
import br.edu.utfpr.pb.labquimica.backend.model.Papel;
import br.edu.utfpr.pb.labquimica.backend.model.PessoaInstituicao;
import br.edu.utfpr.pb.labquimica.backend.model.Usuario;
import br.edu.utfpr.pb.labquimica.backend.repository.CidadeRepository;
import br.edu.utfpr.pb.labquimica.backend.service.impl.CidadeServiceImpl;
import br.edu.utfpr.pb.labquimica.backend.utils.ValidationMessages;


@RunWith(SpringRunner.class)
@ActiveProfiles("test")
public class CidadeServiceTests {

	@SpyBean
	CidadeServiceImpl service;
	
	@MockBean
	CidadeRepository repository;
	
	@Test
	public void deveObterUmaListaDeCidades() {
		
		
	List<Cidade> listaCidades1=new ArrayList<>();
	Cidade cidade1= new Cidade();
	cidade1.setNome("pato branco");
	cidade1.setUf("PR");
	cidade1.setId(1);
	
	Cidade cidade11= new Cidade();
	cidade11.setNome("pato branco");
	cidade11.setUf("SC");
	cidade11.setId(11);
	
	repository.save(cidade1);

	List<Cidade> listaCidades2=new ArrayList<>();
	
	Cidade cidade2= new Cidade();
	cidade2.setNome("xaxim");
	cidade2.setUf("SC");
	cidade2.setId(2);
	
	repository.save(cidade2);
	
	listaCidades1.add(cidade1);
	listaCidades1.add(cidade11);
	listaCidades2.add(cidade2);
		
	
			
			
			Mockito.when(service.findByNomeContainingIgnoreCaseOrderByNomeAsc("pato branco")).thenReturn(listaCidades1);
			
			Mockito.when(repository.findByNomeContainingIgnoreCaseOrderByNomeAsc("xaxim")).thenReturn(listaCidades2);
			
			
			List<Cidade> resultado1= service.findByNomeContainingIgnoreCaseOrderByNomeAsc("pato branco");
			
			 
			
			Assertions.assertThat(resultado1).isNotEmpty().hasSize(2).contains(cidade1,cidade11);
			
			List<Cidade> resultado2= service.findByNomeContainingIgnoreCaseOrderByNomeAsc("xaxim");
			
			Assertions.assertThat(resultado2).isNotEmpty().hasSize(1).contains(cidade2);

			
			
	}
	
	
	@Test
	public void naoDeveEncontrarCidadeSalva() {
		
		
	List<Cidade> listaCidades=new ArrayList<>();
	

		
			
			Mockito.when(repository.findByNomeContainingIgnoreCaseOrderByNomeAsc("pato branco")).thenReturn(listaCidades);
			
			Mockito.when(repository.findByNomeContainingIgnoreCaseOrderByNomeAsc("xaxim")).thenReturn(listaCidades);
			
			
			
			List<Cidade> resultado= service.findByNomeContainingIgnoreCaseOrderByNomeAsc("pato branco");
			
			Assertions.assertThat(resultado).isEmpty();
			
			List<Cidade> resultado2= service.findByNomeContainingIgnoreCaseOrderByNomeAsc("xaxim");
			
			Assertions.assertThat(resultado2).isEmpty();

			
			
			
	}
	@Test
	public void deveLancarErroNaUf() {
		
	
		
		//CENARIO
		
		
		String nome="pato branco";
		Cidade cidade= Cidade.builder().id(1).nome(nome).uf(null).build();
		 
		
		Mockito.doNothing().doThrow(new RuntimeException
				(ValidationMessages.UfNaoPodeSerVazio))
		.when(service).findByNomeContainingIgnoreCaseOrderByNomeAsc(nome);
		
		 Mockito.doNothing().when(service).findByNomeContainingIgnoreCaseOrderByNomeAsc(nome);
		 
		 
		 
		 
		 //VERIFICAR
		 
		 
		 
		 
		 Throwable exception = Assertions.catchThrowable ( () -> service.findByNomeContainingIgnoreCaseOrderByNomeAsc(nome));
			Assertions.assertThat(exception).isInstanceOf(ValidationMessages.class).hasMessage(ValidationMessages.UfNaoPodeSerVazio);
		
		
	}
	
	
	
	
	
	
	
}
