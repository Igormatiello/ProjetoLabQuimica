package br.edu.utfpr.pb.labquimica.backend.testes.modelRepository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertThat;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.hibernate.mapping.Set;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.google.common.base.Optional;

import br.edu.utfpr.pb.labquimica.backend.enumerators.TipoPessoa;
import br.edu.utfpr.pb.labquimica.backend.model.Papel;
import br.edu.utfpr.pb.labquimica.backend.model.Pessoa;
import br.edu.utfpr.pb.labquimica.backend.model.PessoaInstituicao;
import br.edu.utfpr.pb.labquimica.backend.model.Usuario;
import br.edu.utfpr.pb.labquimica.backend.repository.CidadeRepository;
import br.edu.utfpr.pb.labquimica.backend.repository.UsuarioRepository;
import lombok.Data;

@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@DataJpaTest
@AutoConfigureTestDatabase

public class UsuarioRepositoryTests {

	@Autowired
	UsuarioRepository repository;

	@Autowired
	TestEntityManager entityManager;

	@Test

	public void deveSalvarUmUsuarioNaBaseDeDados() {

		Usuario usuario = criarEPersistirUmUsuario();

		Usuario usuarioSalvo = repository.save(usuario);

		Assertions.assertThat(usuarioSalvo.getId()).isNotNull();
	}

	@Test
	public void deveDeletarUmUsuario() {
		Usuario usuario = criarEPersistirUmUsuario();

		usuario = entityManager.find(Usuario.class, usuario.getId());

		repository.delete(usuario);

		Usuario UsuarioInexistente = entityManager.find(Usuario.class, usuario.getId());
		assertThat(UsuarioInexistente).isNull();
	}

	@Test
	public void deveAtualizarUmUsuario() {
		Usuario usuario = criarEPersistirUmUsuario();

		usuario.setPassword("senha atual");
		usuario.setUsername("usuario atual");

		Pessoa pessoa = new Pessoa();
		pessoa.setCep("22222");
		pessoa.setNome("igor");
		pessoa.setEndereco("new ed");
		usuario.setPessoa(pessoa);

		java.util.Set<Papel> papeis;
		papeis = new HashSet<Papel>();

		Papel papel1 = new Papel();
		papel1.setId(2l);
		papel1.setNome("papel n2");

		Papel papel2 = new Papel();
		papel2.setId(1l);
		papel2.setNome("papel n1");

		usuario.setPapeis(papeis);

		repository.save(usuario);

		Usuario usuarioAtualizado = entityManager.find(Usuario.class, usuario.getId());

		Assertions.assertThat(usuarioAtualizado.getPassword()).isEqualTo("senha atual");
		Assertions.assertThat(usuarioAtualizado.getUsername()).isEqualTo("usuario atual");
		Assertions.assertThat(usuarioAtualizado.getPessoa().getCep()).isEqualTo("22222");
		Assertions.assertThat(usuarioAtualizado.getPessoa().getNome()).isEqualTo("igor");
		Assertions.assertThat(usuarioAtualizado.getPessoa().getEndereco()).isEqualTo("new ed");
		Assertions.assertThat(usuarioAtualizado.getPapeis().contains(papel2));
		Assertions.assertThat(usuarioAtualizado.getPapeis().contains(papel1));
	}

	@Test
	public void deveBuscarUmUsuarioPorId() {

		Usuario usuario = criarEPersistirUmUsuario();
		java.util.Optional<Usuario> lancamentoEncontrado = repository.findById(usuario.getId());

		assertThat(lancamentoEncontrado.isPresent()).isTrue();

	}

	@Test
	public void deveBuscarUmUsuarioPorUsername() {

		Usuario usuario = criarEPersistirUmUsuario();
		java.util.Optional<Usuario> lancamentoEncontrado = repository.findByUsername(usuario.getUsername());

		assertThat(lancamentoEncontrado.isPresent()).isTrue();

	}

	private Usuario criarEPersistirUmUsuario() {
		Usuario usuario = criarUsuario();
		entityManager.persist(usuario);
		return usuario;
	}

	public static Usuario criarUsuario() {

		List<PessoaInstituicao> listaVinculos = new ArrayList<>();

		PessoaInstituicao lista1 = new PessoaInstituicao();
		lista1.setCreditoVigente(1000D);
		lista1.setEhAtivo(false);

		listaVinculos.add(lista1);

		PessoaInstituicao lista2 = new PessoaInstituicao();
		lista2.setCreditoVigente(2000D);
		lista2.setEhAtivo(true);

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

		Pessoa pessoa = new Pessoa();
		pessoa.setTipoPessoa(TipoPessoa.Fisica);
		pessoa.setNome("usuario1");
		pessoa.setDocumento("documento 1");
		pessoa.setCep("11111111");
		pessoa.setVinculos(listaVinculos);
		pessoa.setEndereco("avenida brasil");
		pessoa.setBairro("centro");
		pessoa.setNumero("112");
		pessoa.setComplemento("perto da caixa");
		pessoa.setTelefone("49999983513");
		pessoa.setCelular("49999983513");
		pessoa.setEmail("usuario1@email.com");
		pessoa.setInscricaoEstadual("descrição estadual");
		pessoa.setDataCriacao(LocalDate.now());
		pessoa.setEhAtivo(true);
		pessoa.setCidade(null);
		pessoa.setSolicitacaoCadastro(null);

		return Usuario.builder().username("usuario1").password("senha1").enabled(Boolean.TRUE).papeis(papeis)
				.pessoa(pessoa).build();

	}

}
