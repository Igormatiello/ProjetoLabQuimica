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
		
		
	List<Cidade> listaCidades=new ArrayList<>();
	Cidade cidade1= new Cidade();
	cidade1.setNome("pato branco");
	cidade1.setUf("PR");
	cidade1.setId(1);
	
	Cidade cidade2= new Cidade();
	cidade2.setNome("xaxim");
	cidade2.setUf("SC");
	cidade2.setId(2);
	
	listaCidades.add(cidade1);
	listaCidades.add(cidade2);
		
	
			
			
			Mockito.when(repository.findByNomeContainingIgnoreCaseOrderByNomeAsc("pato branco")).thenReturn(listaCidades);
			
			Mockito.when(repository.findByNomeContainingIgnoreCaseOrderByNomeAsc("xaxim")).thenReturn(listaCidades);
			
			
			
			List<Cidade> resultado= service.findByNomeContainingIgnoreCaseOrderByNomeAsc("pato branco");
			
			Assertions.assertThat(resultado).isNotEmpty().hasSize(1).contains(cidade1);
			
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
		 
		
		 Mockito.doReturn(cidade).when(service).findByNomeContainingIgnoreCaseOrderByNomeAsc(nome);
		 
		 
		 //VERIFICAR
		 
		 Throwable exception = Assertions.catchThrowable ( () -> service.findByNomeContainingIgnoreCaseOrderByNomeAsc(nome));
			Assertions.assertThat(exception).isInstanceOf(ValidationMessages.class).hasMessage(ValidationMessages.UfNaoPodeSerVazio);
		
		
	}
	
	
	
	
	
	
	
}
