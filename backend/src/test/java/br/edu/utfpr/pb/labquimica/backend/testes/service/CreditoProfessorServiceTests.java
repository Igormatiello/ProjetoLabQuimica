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


import br.edu.utfpr.pb.labquimica.backend.enumerators.TipoPessoa;
import br.edu.utfpr.pb.labquimica.backend.model.Cidade;
import br.edu.utfpr.pb.labquimica.backend.model.CreditoProfessor;
import br.edu.utfpr.pb.labquimica.backend.model.Pessoa;
import br.edu.utfpr.pb.labquimica.backend.model.PessoaInstituicao;
import br.edu.utfpr.pb.labquimica.backend.model.SolicitacaoCadastro;
import br.edu.utfpr.pb.labquimica.backend.model.Usuario;
import br.edu.utfpr.pb.labquimica.backend.repository.CreditoProfessorRepository;
import br.edu.utfpr.pb.labquimica.backend.service.impl.CreditoProfessorServiceImpl;
import br.edu.utfpr.pb.labquimica.backend.viewmodels.ResultadoOperacaoViewModel;

@RunWith(SpringRunner.class)
@ActiveProfiles("test")

public class CreditoProfessorServiceTests {

	@SpyBean
	CreditoProfessorServiceImpl service;
	
	@MockBean
	CreditoProfessorRepository repository;
	
	
	@Test
	public void deveSalvarUmLancamento() {
		
		
		
		Cidade cidade= new Cidade();
		cidade.setId(1);
		cidade.setNome("xaxim");
		cidade.setUf("SC");
		
		
		List<PessoaInstituicao> listaVinculos = new ArrayList<>();
		PessoaInstituicao lista1=new PessoaInstituicao();
		lista1.setId(1L);
		lista1.setEhProfessor(true);
		
		listaVinculos.add(lista1);
		
		PessoaInstituicao lista2=new PessoaInstituicao();
		lista2.setId(2L);
		lista2.setEhProfessor(false);
		
		listaVinculos.add(lista2);
		
		
		Usuario usuario = new Usuario();
		usuario.setId(1L);
		
		
	
		SolicitacaoCadastro solicitacaoCadastro = new SolicitacaoCadastro();
		solicitacaoCadastro.setCpfOrientador("000");
		
		
			Pessoa pessoa=	Pessoa
				.builder()
				.tipoPessoa(TipoPessoa.Fisica)
				.nome("pessoa1")
				.documento("6666")
				.cep("1111")
				.endereco("endereço1")
				.bairro("centro")
				.numero("224")
				.complemento("na avenida")
				.telefone("49999999")
				.celular("499999999")
				.email("pessoa1@email.com")
				.inscricaoEstadual("ie1")
				.dataCriacao(LocalDate.now())
				.ehAtivo(true)
				.cidade(cidade)
				.vinculos(listaVinculos)
				.solicitacaoCadastro(solicitacaoCadastro)
				.usuario(usuario)
				.build();
		
		
		CreditoProfessor cp= new CreditoProfessor()
				.builder()
				.id(1l)
				.pessoa(pessoa)
				.nomeProjeto("nome do projeto")
				.valorSaldo(1000d)
				.valorCredito(1000d)
				.build();
		

		repository.save(cp);		
		
		ResultadoOperacaoViewModel<CreditoProfessor> resultado= service.saveWithValidation(cp);
		
		Assertions.assertThat(resultado).isNotNull();
		
		  List<CreditoProfessor> cpFile = service.findAll();

	        Assertions.assertThat( cpFile.size()).isEqualTo(1);
	      

	       
		
		
		
	}
	
}
