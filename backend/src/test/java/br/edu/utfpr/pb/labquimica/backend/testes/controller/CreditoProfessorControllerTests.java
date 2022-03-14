package br.edu.utfpr.pb.labquimica.backend.testes.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.Test;

import br.edu.utfpr.pb.labquimica.backend.controller.CreditoProfessorController;
import br.edu.utfpr.pb.labquimica.backend.enumerators.TipoPessoa;
import br.edu.utfpr.pb.labquimica.backend.model.Cidade;
import br.edu.utfpr.pb.labquimica.backend.model.CreditoProfessor;
import br.edu.utfpr.pb.labquimica.backend.model.Pessoa;
import br.edu.utfpr.pb.labquimica.backend.model.PessoaInstituicao;
import br.edu.utfpr.pb.labquimica.backend.model.SolicitacaoCadastro;
import br.edu.utfpr.pb.labquimica.backend.model.Usuario;
import br.edu.utfpr.pb.labquimica.backend.repository.CreditoProfessorRepository;
import br.edu.utfpr.pb.labquimica.backend.viewmodels.ResultadoOperacaoViewModel;

public class CreditoProfessorControllerTests {

	
	CreditoProfessorRepository creditoProfessorRepository;
	
	CreditoProfessorController controller;
	
	
	
	@Test 
	public void deveSalvarAprovacao() {
		
		Cidade cidade = new Cidade();
		cidade.setId(1);
		cidade.setNome("xaxim");
		cidade.setUf("SC");

		List<PessoaInstituicao> listaVinculos = new ArrayList<>();
		PessoaInstituicao lista1 = new PessoaInstituicao();
		lista1.setId(1L);
		lista1.setEhProfessor(true);

		listaVinculos.add(lista1);

		PessoaInstituicao lista2 = new PessoaInstituicao();
		lista2.setId(2L);
		lista2.setEhProfessor(false);

		listaVinculos.add(lista2);

		Usuario usuario = new Usuario();
		usuario.setId(1L);

		SolicitacaoCadastro solicitacaoCadastro = new SolicitacaoCadastro();
		solicitacaoCadastro.setCpfOrientador("000");

		Pessoa pessoa = Pessoa.builder().tipoPessoa(TipoPessoa.Fisica).nome("pessoa1").documento("6666").cep("1111")
				.endereco("endereço1").bairro("centro").numero("224").complemento("na avenida").telefone("49999999")
				.celular("499999999").email("pessoa1@email.com").inscricaoEstadual("ie1").dataCriacao(LocalDate.now())
				.ehAtivo(true).cidade(cidade).vinculos(listaVinculos).solicitacaoCadastro(solicitacaoCadastro)
				.usuario(usuario).build();

		CreditoProfessor cp = new CreditoProfessor().builder().id(1l).pessoa(pessoa).nomeProjeto("nome do projeto")
				.valorSaldo(1000d).valorCredito(1000d).build();
		
		
		ResultadoOperacaoViewModel<CreditoProfessor> resultado= controller.saveAprovacao(cp);
		
		
		Assertions.assertThat(resultado.returningSuccess());
		
		Assertions.assertThat(controller.saveAprovacao(cp)).returns(null, null);
		
		}
	
	
	
	@Test
	public void deveBuscarCreditosPorProfessorId(){
		
		Cidade cidade = new Cidade();
		cidade.setId(1);
		cidade.setNome("xaxim");
		cidade.setUf("SC");

		List<PessoaInstituicao> listaVinculos = new ArrayList<>();
		PessoaInstituicao lista1 = new PessoaInstituicao();
		lista1.setId(1L);
		lista1.setEhProfessor(true);

		listaVinculos.add(lista1);

		PessoaInstituicao lista2 = new PessoaInstituicao();
		lista2.setId(2L);
		lista2.setEhProfessor(true);

		listaVinculos.add(lista2);

		Usuario usuario = new Usuario();
		usuario.setId(1L);

		SolicitacaoCadastro solicitacaoCadastro = new SolicitacaoCadastro();
		solicitacaoCadastro.setCpfOrientador("000");

		
		Long id = 1l;
		Pessoa pessoa = Pessoa.builder().id(id).tipoPessoa(TipoPessoa.Fisica).nome("pessoa1").documento("6666").cep("1111")
				.endereco("endereço1").bairro("centro").numero("224").complemento("na avenida").telefone("49999999")
				.celular("499999999").email("pessoa1@email.com").inscricaoEstadual("ie1").dataCriacao(LocalDate.now())
				.ehAtivo(true).cidade(cidade).vinculos(listaVinculos).solicitacaoCadastro(solicitacaoCadastro)
				.usuario(usuario).build();

		CreditoProfessor cp = new CreditoProfessor().builder().id(1l).pessoa(pessoa).nomeProjeto("nome do projeto")
				.valorSaldo(1000d).valorCredito(1000d).build();
		
		
		List<CreditoProfessor> resultado= controller.buscaCreditosPorProfessor(id);
		
		Assertions.assertThat(resultado).contains(cp);
		
		
	}

	
	
	
	

	}
	
	

