package br.edu.utfpr.pb.labquimica.backend.testes.service;

import java.util.Arrays;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.hibernate.criterion.Example;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import br.edu.utfpr.pb.labquimica.backend.model.Instituicao;
import br.edu.utfpr.pb.labquimica.backend.model.Pessoa;
import br.edu.utfpr.pb.labquimica.backend.model.PessoaInstituicao;
import br.edu.utfpr.pb.labquimica.backend.model.Usuario;
import br.edu.utfpr.pb.labquimica.backend.repository.InstituicaoRepository;
import br.edu.utfpr.pb.labquimica.backend.repository.PessoaInstituicaoRepository;
import br.edu.utfpr.pb.labquimica.backend.repository.PessoaRepository;
import br.edu.utfpr.pb.labquimica.backend.repository.UsuarioRepository;
import br.edu.utfpr.pb.labquimica.backend.service.impl.PessoaInstituicaoServiceImpl;
import br.edu.utfpr.pb.labquimica.backend.testes.modelRepository.InstituicaoRepositoryTests;
import br.edu.utfpr.pb.labquimica.backend.testes.modelRepository.PessoaRepositoryTests;
import br.edu.utfpr.pb.labquimica.backend.testes.modelRepository.PessoalInstituicaoRepositoryTests;
import br.edu.utfpr.pb.labquimica.backend.testes.modelRepository.UsuarioRepositoryTests;

@RunWith(SpringRunner.class)
@ActiveProfiles("test")


public class PessoaInstituicaoServiceTests {

	@SpyBean
	PessoaInstituicaoServiceImpl service;
	
	@MockBean
	PessoaInstituicaoRepository repository;
	
	PessoalInstituicaoRepositoryTests PessoaInstituicaoTest;
	
	PessoaRepository PessoaRepository;
	
	PessoaRepositoryTests PessoaTest;
	
	InstituicaoRepositoryTests InstituicaoTest;
	
	InstituicaoRepository InstituicaoRepository;
	
	UsuarioRepositoryTests UsuarioTest;
	
	UsuarioRepository UsuarioRepository;
	
	
	@Test
	public void deveRetornarPessoaInstituicaoPeloNomeECpf() {
		
		
		Pessoa pessoa= PessoaTest.criarPessoa();
		pessoa.setDocumento("111111111");
		
		PessoaRepository.save(pessoa);
		
		Instituicao instituicao = InstituicaoTest.criarInstituicao();
		instituicao.setNome("utfpr");
		
		InstituicaoRepository.save(instituicao);
		
		PessoaInstituicao pessoaInstituicao= PessoaInstituicaoTest.criarPessoaInstituicao();
		pessoaInstituicao.setEhAtivo(true);
		pessoaInstituicao.setInstituicao(instituicao);
		pessoaInstituicao.setPessoa(pessoa);
		
		repository.save(pessoaInstituicao);
		
		
		PessoaInstituicao resultado= service.findByInstituicaoNomeAndPessoaDocumentoAndEhProfessorTrue("utfpr", "111111111");
		
		Assertions.assertThat(resultado).isNotNull();
		
	}
	@Test
	public void deveRetornarFalsoPessoaInstituicaoPeloNomeECpf() {
		
		
		Pessoa pessoa= PessoaTest.criarPessoa();
		pessoa.setDocumento("111111111");
		
		//PessoaRepository.save(pessoa);
		
		Instituicao instituicao = InstituicaoTest.criarInstituicao();
		instituicao.setNome("utfpr");
		
		//InstituicaoRepository.save(instituicao);
		
		PessoaInstituicao pessoaInstituicao= PessoaInstituicaoTest.criarPessoaInstituicao();
		pessoaInstituicao.setEhAtivo(true);
		pessoaInstituicao.setInstituicao(instituicao);
		pessoaInstituicao.setPessoa(pessoa);
		
		//repository.save(pessoaInstituicao);
		
		
		PessoaInstituicao resultado= service.findByInstituicaoNomeAndPessoaDocumentoAndEhProfessorTrue("utfpr", "111111111");
		
		Assertions.assertThat(resultado).isNull();
		
	}
	
	@Test
	public void deveBuscarPessoaInstituicaoPeloIdDoUsuario() {
		
		
		
		
				
		Usuario usuario= UsuarioTest.criarUsuario();
		usuario.setId(1l);
		

		
		
		Mockito.when(UsuarioRepository.save(usuario)).thenReturn(usuario);
		
		UsuarioRepository.save(usuario);
		
		
		
		Pessoa pessoa= PessoaTest.criarPessoa();
		pessoa.setUsuario(usuario);		
		
		Mockito.when(PessoaRepository.save(pessoa)).thenReturn(pessoa);
		
		PessoaRepository.save(pessoa);
		
		
		
		Instituicao instituicao = InstituicaoTest.criarInstituicao();
		
		Mockito.when(InstituicaoRepository.save(instituicao)).thenReturn(instituicao);
		
		InstituicaoRepository.save(instituicao);
		
		
		
		PessoaInstituicao pessoaInstituicao= PessoaInstituicaoTest.criarPessoaInstituicao();
		pessoaInstituicao.setEhAtivo(true);
		pessoaInstituicao.setInstituicao(instituicao);
		pessoaInstituicao.setPessoa(pessoa);
		
		Mockito.when(repository.save(pessoaInstituicao)).thenReturn(pessoaInstituicao);
		
		repository.save(pessoaInstituicao);
		
		
		
		
		  List<PessoaInstituicao> resultado=  service.findPessoaInstituicao(1l);
		
		
		  Assertions.assertThat(resultado).contains(pessoaInstituicao);
		  
	}
	@Test
	public void deveBuscarPessoaInstituicaoPeloIdPessoa() {
		
		//cenario
		Pessoa pessoa= PessoaTest.criarPessoa();
		pessoa.setId(1l);
		
	List<Pessoa> lista = Arrays.asList(pessoa);
	
	
	
	Mockito.when(PessoaRepository.findAllById(Mockito.any())).thenReturn(lista);
	
	
	//execução
	
	List<PessoaInstituicao> resultado = service.findByPessoaId(1l);
	
	//verificações
	
	Assertions.assertThat(resultado)
	.isNotEmpty()
	.hasSize(1);
	//.contains(lista);
	
	
	
	}
	
	
	
	
	
}
