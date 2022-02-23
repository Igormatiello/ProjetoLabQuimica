package br.edu.utfpr.pb.labquimica.backend.testes.modelRepository;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

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
import br.edu.utfpr.pb.labquimica.backend.model.Instituicao;
import br.edu.utfpr.pb.labquimica.backend.model.Papel;
import br.edu.utfpr.pb.labquimica.backend.model.Pessoa;
import br.edu.utfpr.pb.labquimica.backend.model.PessoaInstituicao;
import br.edu.utfpr.pb.labquimica.backend.model.Usuario;
import br.edu.utfpr.pb.labquimica.backend.repository.CidadeRepository;
import br.edu.utfpr.pb.labquimica.backend.repository.PessoaInstituicaoRepository;
import br.edu.utfpr.pb.labquimica.backend.repository.PessoaRepository;

@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@DataJpaTest
@AutoConfigureTestDatabase

public class PessoalInstituicaoRepositoryTests {

	@Autowired
	PessoaInstituicaoRepository repository;

	@Autowired
	TestEntityManager entityManager;

	@Test

	public void deveSalvarUmaPessoaInstituicaoNaBaseDeDados() {

		PessoaInstituicao pessoaInstituicao = criarEPersistirUmaPessoaInstituicao();

		PessoaInstituicao pessoaInstituicaoSalva = repository.save(pessoaInstituicao);

		Assertions.assertThat(pessoaInstituicao.getId()).isNotNull();
	}

	@Test
	public void deveDeletarUmaPessoaInstituicao() {
		PessoaInstituicao pessoaInstituicao = criarEPersistirUmaPessoaInstituicao();

		pessoaInstituicao = entityManager.find(PessoaInstituicao.class, pessoaInstituicao.getId());

		repository.delete(pessoaInstituicao);

		PessoaInstituicao pessoaInstituicaoInexistente = entityManager.find(PessoaInstituicao.class,
				pessoaInstituicao.getId());
		assertThat(pessoaInstituicaoInexistente).isNull();
	}

	@Test
	public void deveAtualizarUmaPessoaInstituicao() {
		PessoaInstituicao pessoaInstituicao = criarEPersistirUmaPessoaInstituicao();

		pessoaInstituicao.setEhAtivo(true);
		pessoaInstituicao.setEhProfessor(true);
		pessoaInstituicao.setCreditoVigente(1200D);

		Instituicao instituicao = new Instituicao();
		instituicao.setNome("ifsc");
		instituicao.setTipoInstituicao(TipoInstituicao.Externa);

		pessoaInstituicao.setInstituicao(instituicao);

		Pessoa pessoa = new Pessoa();
		pessoa.setCep("12120000");
		pessoa.setEmail("email@email.com");

		pessoaInstituicao.setPessoa(pessoa);

		repository.save(pessoaInstituicao);

		PessoaInstituicao pessoaInstituicaoAtualizada = entityManager.find(PessoaInstituicao.class,
				pessoaInstituicao.getId());

		Assertions.assertThat(pessoaInstituicaoAtualizada.getCreditoVigente()).isEqualTo(1200D);
		Assertions.assertThat(pessoaInstituicaoAtualizada.getInstituicao().getNome()).isEqualTo("ifsc");
		Assertions.assertThat(pessoaInstituicaoAtualizada.getInstituicao().getTipoInstituicao())
				.isEqualTo(TipoInstituicao.Externa);
		Assertions.assertThat(pessoaInstituicaoAtualizada.getPessoa().getCep()).isEqualTo("12120000");
		Assertions.assertThat(pessoaInstituicaoAtualizada.getPessoa().getEmail()).isEqualTo("email@email.com");

	}

	@Test
	public void deveBuscarUmaPessoaInstituicaoPorId() {

		PessoaInstituicao pessoaInstituicao = criarEPersistirUmaPessoaInstituicao();
		java.util.Optional<PessoaInstituicao> pIEncontrado = repository.findById(pessoaInstituicao.getId());

		assertThat(pIEncontrado.isPresent()).isTrue();

	}

	private PessoaInstituicao criarEPersistirUmaPessoaInstituicao() {
		PessoaInstituicao pessoaInstituicao = criarPessoaInstituicao();
		entityManager.persist(pessoaInstituicao);
		return pessoaInstituicao;
	}

	public static PessoaInstituicao criarPessoaInstituicao() {

		Instituicao instituicao = new Instituicao();
		instituicao.setTipoInstituicao(TipoInstituicao.Interna);
		instituicao.setNome("utfpr");

		Pessoa pessoa = new Pessoa();
		pessoa.setCep("89860000");
		pessoa.setEmail("usuario@email.com");

		return PessoaInstituicao.builder().instituicao(instituicao).pessoa(pessoa).ehAtivo(false).creditoVigente(2300D)
				.ehProfessor(false).build();

	}

}
