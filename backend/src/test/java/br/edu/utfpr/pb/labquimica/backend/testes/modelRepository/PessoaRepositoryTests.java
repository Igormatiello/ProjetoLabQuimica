package br.edu.utfpr.pb.labquimica.backend.testes.modelRepository;

import static org.assertj.core.api.Assertions.assertThat;

import java.sql.Date;
import java.text.DateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import br.edu.utfpr.pb.labquimica.backend.enumerators.TipoInstituicao;
import br.edu.utfpr.pb.labquimica.backend.enumerators.TipoPessoa;
import br.edu.utfpr.pb.labquimica.backend.model.Cidade;
import br.edu.utfpr.pb.labquimica.backend.model.Instituicao;
import br.edu.utfpr.pb.labquimica.backend.model.Papel;
import br.edu.utfpr.pb.labquimica.backend.model.Pessoa;
import br.edu.utfpr.pb.labquimica.backend.model.PessoaInstituicao;
import br.edu.utfpr.pb.labquimica.backend.model.SolicitacaoCadastro;
import br.edu.utfpr.pb.labquimica.backend.model.Usuario;
import br.edu.utfpr.pb.labquimica.backend.repository.CidadeRepository;
import br.edu.utfpr.pb.labquimica.backend.repository.PessoaRepository;

@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@DataJpaTest
@AutoConfigureTestDatabase

public class PessoaRepositoryTests {

	@Autowired
	PessoaRepository repository;

	@Autowired
	TestEntityManager entityManager;

	@Test
	public void devePersistirUmaPessoaNaBaseDeDados() {

		Pessoa pessoa = criarPessoa();

		Pessoa pessoaSalva = repository.save(pessoa);

		Assertions.assertThat(pessoaSalva.getId()).isNotNull();

	}

	@Test
	public void deverRetornarFalsoQuandoNaoHouverUmaPessoaNaBaseDeDados() {

		boolean resultado = repository.existsById(1000L);

		Assertions.assertThat(resultado).isFalse();

	}

	@Test
	public void deveBuscarUmaPessoaPorId() {

		Pessoa pessoa = criarEPersistirUmaPessoa();
		java.util.Optional<Pessoa> pessoaEncontrada = repository.findById(pessoa.getId());

		assertThat(pessoaEncontrada.isPresent()).isTrue();

	}

	@Test
	public void deveRetornarFalsoAoBuscarUmaPessoaPorIdNaoCadastrada() {

		boolean resultado = repository.existsById(1000L);

		assertThat(resultado).isFalse();

	}

	@Test
	public void deveVerificarAExistenciaDeUmaPessoaPorMeioDeDocumento() {

		Pessoa pessoa = criarEPersistirUmaPessoa();
		entityManager.persist(pessoa);

		boolean resultado = repository.existsByDocumento("6666");

		Assertions.assertThat(resultado).isTrue();

	}

	@Test
	public void deveRetornarFalsoQuandoNaoHouverPessoaCadastradaComODocumento() {

		boolean resultado = repository.existsByDocumento("1111");

		Assertions.assertThat(resultado).isFalse();
	}

	@Test
	public void deveAtualizarUmCadastro() {
		Pessoa pessoa = criarEPersistirUmaPessoa();

		pessoa.setEndereco("novo endereço");
		pessoa.setCelular("1212");

		Usuario usuario = new Usuario();
		usuario.setId(4l);
		pessoa.setUsuario(usuario);

		List<PessoaInstituicao> listaVinculos = new ArrayList<>();
		PessoaInstituicao lista1 = new PessoaInstituicao();
		lista1.setId(2L);
		lista1.setEhProfessor(false);

		listaVinculos.add(lista1);

		PessoaInstituicao lista2 = new PessoaInstituicao();
		lista2.setId(3L);
		lista2.setEhProfessor(true);

		listaVinculos.add(lista2);

		pessoa.setVinculos(listaVinculos);
		repository.save(pessoa);

		Pessoa pessoaAtualizada = entityManager.find(Pessoa.class, pessoa.getId());

		assertThat(pessoaAtualizada.getEndereco()).isEqualTo("novo endereço");
		assertThat(pessoaAtualizada.getCelular()).isEqualTo("1212");
		assertThat(pessoaAtualizada.getUsuario().getId()).isEqualTo(4l);
		assertThat(pessoaAtualizada.getVinculos().get(0).getId()).isEqualTo(2L);
		assertThat(pessoaAtualizada.getVinculos().get(1).getId()).isEqualTo(3L);

	}

	@Test
	public void deveDeletarUmaPessoa() {
		Pessoa pessoa = criarEPersistirUmaPessoa();

		pessoa = entityManager.find(Pessoa.class, pessoa.getId());

		repository.delete(pessoa);

		Pessoa pessoaInexistente = entityManager.find(Pessoa.class, pessoa.getId());
		assertThat(pessoaInexistente).isNull();
	}

	private Pessoa criarEPersistirUmaPessoa() {
		Pessoa pessoa = criarPessoa();
		entityManager.persist(pessoa);
		return pessoa;
	}

	public static Pessoa criarPessoa() {

		Cidade cidade = new Cidade();
		cidade.setId(1);
		cidade.setNome("xaxim");
		cidade.setUf("SC");

		Cidade cidade2 = new Cidade();
		cidade2.setNome("cidade padrão");
		cidade2.setUf("uf padrão");
		cidade2.setId(1);

		Instituicao instituicao = new Instituicao();
		instituicao.setNome("Utfpr");
		instituicao.setCidade(cidade2);
		instituicao.setId(1);
		instituicao.setTipoInstituicao(TipoInstituicao.Interna);

		List<PessoaInstituicao> listaVinculos = new ArrayList<>();
		PessoaInstituicao lista1 = new PessoaInstituicao();
		lista1.setId(1L);
		lista1.setEhProfessor(true);
		lista1.setInstituicao(instituicao);
		lista1.setPessoa(null);
		lista1.setCreditoVigente(200.00);
		lista1.setEhAtivo(false);

		listaVinculos.add(lista1);

		PessoaInstituicao lista2 = new PessoaInstituicao();
		lista2.setId(2L);
		lista2.setEhProfessor(false);
		lista1.setInstituicao(instituicao);
		lista1.setPessoa(null);
		lista1.setCreditoVigente(300.00);
		lista1.setEhAtivo(true);

		listaVinculos.add(lista2);

		java.util.Set<Papel> papeis;
		papeis = new HashSet<Papel>();

		Papel papel1 = new Papel();
		papel1.setId(1l);
		papel1.setNome("papel n1");

		Papel papel2 = new Papel();
		papel2.setId(2l);
		papel2.setNome("papel n2");

		papeis.add(papel1);
		papeis.add(papel2);

		Usuario usuario = new Usuario();
		usuario.setId(1L);
		usuario.setJaCriptografada(true);
		usuario.setLastPasswordReset(Date.valueOf("11/12/2021"));
		usuario.setPapeis(papeis);
		usuario.setPassword("1234");
		usuario.setPessoa(null);
		usuario.setUsername("username");

		SolicitacaoCadastro solicitacaoCadastro = new SolicitacaoCadastro();
		solicitacaoCadastro.setCpfOrientador("000");
		solicitacaoCadastro.setBairro("centro");
		solicitacaoCadastro.setTipoPessoa(TipoPessoa.Fisica);
		solicitacaoCadastro.setNome("pessoa1");
		solicitacaoCadastro.setDocumento("6666");
		solicitacaoCadastro.setCep("1111");
		solicitacaoCadastro.setEndereco("endereço1");
		solicitacaoCadastro.setNumero("224");
		solicitacaoCadastro.setComplemento("na avenida");
		solicitacaoCadastro.setTelefone("49999999");
		solicitacaoCadastro.setCelular("499999999");
		solicitacaoCadastro.setEmail("pessoa1@email.com");
		solicitacaoCadastro.setDataCriacao(LocalDate.now());
		solicitacaoCadastro.setCidade(cidade2);
		solicitacaoCadastro.setCpfOrientador("");
		solicitacaoCadastro.setDataTerminoProgramaEnsino(LocalDate.MAX);
		solicitacaoCadastro.setEhProfessor(false);
		solicitacaoCadastro.setId(1l);
		solicitacaoCadastro.setInscricaoEstadual("inscrição estadual");
		solicitacaoCadastro.setNomeInstituicao("utfpr");
		solicitacaoCadastro.setSenha("1234");
		solicitacaoCadastro.setPessoa(null);
		solicitacaoCadastro.setNomeprojeto("projeto");
		solicitacaoCadastro.setNomeProgramaEnsino("programa de ensino");

		return Pessoa.builder().id(1l).tipoPessoa(TipoPessoa.Fisica).nome("pessoa1").documento("6666").cep("1111")
				.endereco("endereço1").bairro("centro").numero("224").complemento("na avenida").telefone("49999999")
				.celular("499999999").email("pessoa1@email.com").inscricaoEstadual("ie1").dataCriacao(LocalDate.now())
				.ehAtivo(true).cidade(cidade).vinculos(listaVinculos).solicitacaoCadastro(solicitacaoCadastro)
				.usuario(usuario).build();

	}

}
