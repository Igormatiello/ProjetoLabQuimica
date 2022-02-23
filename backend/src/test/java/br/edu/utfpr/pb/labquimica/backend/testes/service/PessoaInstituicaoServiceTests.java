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
import br.edu.utfpr.pb.labquimica.backend.testes.modelRepository.CriarObjetos;
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

	@Test
	public void deveRetornarPessoaInstituicaoPeloNomeECpf() {

		Pessoa pessoa = CriarObjetos.criarPessoa();
		String doc = pessoa.getDocumento();

		Instituicao instituicao = CriarObjetos.criarInstituicao();
		String nome = instituicao.getNome();

		PessoaInstituicao pessoaInstituicao = CriarObjetos.criarPessoaInstituicao();
		Long id = pessoaInstituicao.getId();
		pessoaInstituicao.setEhAtivo(true);
		pessoaInstituicao.setInstituicao(instituicao);
		pessoaInstituicao.setPessoa(pessoa);

		repository.save(pessoaInstituicao);

		Mockito.when(service.findByInstituicaoNomeAndPessoaDocumentoAndEhProfessorTrue(nome, doc))
				.thenReturn(pessoaInstituicao);

		PessoaInstituicao resultado = service.findByInstituicaoNomeAndPessoaDocumentoAndEhProfessorTrue(nome, doc);

		Assertions.assertThat(resultado).isNotNull();
		Assertions.assertThat(resultado.getId()).isEqualByComparingTo(id);

	}

	@Test
	public void deveRetornarFalsoPessoaInstituicaoPeloNomeECpf() {

		Pessoa pessoa = CriarObjetos.criarPessoa();
		String doc = pessoa.getDocumento();

		Instituicao instituicao = CriarObjetos.criarInstituicao();
		String nome = instituicao.getNome();

		PessoaInstituicao pessoaInstituicao = CriarObjetos.criarPessoaInstituicao();
		Long id = pessoaInstituicao.getId();
		pessoaInstituicao.setEhAtivo(false);
		pessoaInstituicao.setInstituicao(null);
		pessoaInstituicao.setPessoa(null);

		repository.save(pessoaInstituicao);

		Mockito.when(service.findByInstituicaoNomeAndPessoaDocumentoAndEhProfessorTrue(nome, doc))
				.thenReturn(pessoaInstituicao);

		PessoaInstituicao resultado = service.findByInstituicaoNomeAndPessoaDocumentoAndEhProfessorTrue(nome, doc);

		Assertions.assertThat(resultado).isNull();

	}

	@Test
	public void deveBuscarPessoaInstituicaoPeloIdDoUsuario() {

		Usuario usuario = CriarObjetos.criarUsuario();
		Long id = usuario.getId();

		Pessoa pessoa = CriarObjetos.criarPessoa();
		pessoa.setUsuario(usuario);

		Instituicao instituicao = CriarObjetos.criarInstituicao();

		PessoaInstituicao pessoaInstituicao = CriarObjetos.criarPessoaInstituicao();
		pessoaInstituicao.setEhAtivo(true);
		pessoaInstituicao.setInstituicao(instituicao);
		pessoaInstituicao.setPessoa(pessoa);

		Mockito.when(repository.save(pessoaInstituicao)).thenReturn(pessoaInstituicao);

		repository.save(pessoaInstituicao);

		List<PessoaInstituicao> resultado = service.findPessoaInstituicao(id);

		Assertions.assertThat(resultado).contains(pessoaInstituicao);
		Assertions.assertThat(resultado).hasSize(1);

	}

	@Test
	public void deveBuscarPessoaInstituicaoPeloIdPessoa() {

		Pessoa pessoa = CriarObjetos.criarPessoa();
		Long idPessoa = pessoa.getId();

		Instituicao instituicao = CriarObjetos.criarInstituicao();

		PessoaInstituicao pessoaInstituicao = CriarObjetos.criarPessoaInstituicao();
		pessoaInstituicao.setEhAtivo(true);
		pessoaInstituicao.setInstituicao(instituicao);
		pessoaInstituicao.setPessoa(pessoa);

		Mockito.when(repository.save(pessoaInstituicao)).thenReturn(pessoaInstituicao);

		repository.save(pessoaInstituicao);

		List<PessoaInstituicao> resultado = service.findByPessoaId(idPessoa);

		Assertions.assertThat(resultado).contains(pessoaInstituicao);
		Assertions.assertThat(resultado).hasSize(1);
	}

	@Test
	public void deveBuscarPessoaInstituicaoPeloIdInstituicao() {

		Pessoa pessoa = CriarObjetos.criarPessoa();

		Instituicao instituicao = CriarObjetos.criarInstituicao();
		Integer id = instituicao.getId();

		PessoaInstituicao pessoaInstituicao = CriarObjetos.criarPessoaInstituicao();
		pessoaInstituicao.setEhAtivo(true);
		pessoaInstituicao.setInstituicao(instituicao);
		pessoaInstituicao.setPessoa(pessoa);

		Mockito.when(repository.save(pessoaInstituicao)).thenReturn(pessoaInstituicao);

		repository.save(pessoaInstituicao);

		List<PessoaInstituicao> resultado = service.findByInstituicao(id);
		Assertions.assertThat(resultado).contains(pessoaInstituicao);
		Assertions.assertThat(resultado).hasSize(1);
	}

	@Test
	public void deveRetornarVazioQuandoBuscarPessoaInstituicaoPeloIdPessoa() {

		Pessoa pessoa = CriarObjetos.criarPessoa();
		Long idPessoa = pessoa.getId();
		idPessoa = idPessoa - 19;

		Instituicao instituicao = CriarObjetos.criarInstituicao();

		PessoaInstituicao pessoaInstituicao = CriarObjetos.criarPessoaInstituicao();
		pessoaInstituicao.setEhAtivo(true);
		pessoaInstituicao.setInstituicao(instituicao);
		pessoaInstituicao.setPessoa(pessoa);

		Mockito.when(repository.save(pessoaInstituicao)).thenReturn(pessoaInstituicao);

		repository.save(pessoaInstituicao);

		List<PessoaInstituicao> resultado = service.findByPessoaId(idPessoa);

		Assertions.assertThat(resultado).isEmpty();

	}

}
