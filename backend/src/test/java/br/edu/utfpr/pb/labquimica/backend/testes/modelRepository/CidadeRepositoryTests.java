package br.edu.utfpr.pb.labquimica.backend.testes.modelRepository;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.google.common.base.Optional;

import br.edu.utfpr.pb.labquimica.backend.model.Cidade;
import br.edu.utfpr.pb.labquimica.backend.repository.CidadeRepository;

@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@DataJpaTest
@AutoConfigureTestDatabase

public class CidadeRepositoryTests {

	@Autowired
	CidadeRepository repository;

	@Autowired
	TestEntityManager entityManager;

	@Test
	public void deveSalvarUmaCidade() {

		Cidade cidade = criarCidade();

		cidade = repository.save(cidade);
		Assertions.assertThat(cidade.getId()).isNotNull();

	}

	@Test
	public void deveDeletarUmaCidade() {

		Cidade cidade = criarEPersistirUmaCidade();

		repository.delete(cidade);

		Cidade cidadeDeletada = entityManager.find(Cidade.class, cidade.getId());

		Assertions.assertThat(cidadeDeletada).isNull();
	}

	@Test
	public void deveBuscarUmaCidadePorId() {
		Cidade cidade = criarEPersistirUmaCidade();

		java.util.Optional<Cidade> cidadeEncontrada = repository.findById(cidade.getId());

		Assertions.assertThat(cidadeEncontrada.isPresent()).isNotNull();

	}

	@Test
	public void deveRetornarFalsoQuandoProcurarUmaCidadeNaoCadastradaPorId() {

		boolean cidadeEncontrada = repository.existsById(3);

		Assertions.assertThat(cidadeEncontrada).isNull();

	}

	@Test
	public void deveAtualizarUmaCidade() {

		Cidade cidade = criarEPersistirUmaCidade();

		cidade.setNome("xaxim");
		cidade.setUf("SC");

		repository.save(cidade);

		Cidade cidadeAtualizada = entityManager.find(Cidade.class, cidade.getId());

		Assertions.assertThat(cidadeAtualizada.getNome()).isEqualTo("xaxim");
		Assertions.assertThat(cidadeAtualizada.getUf()).isEqualTo("SC");

	}

	private Cidade criarEPersistirUmaCidade() {

		Cidade cidade = criarCidade();
		entityManager.persist(cidade);
		return cidade;

	}

	public static Cidade criarCidade() {

		return Cidade.builder().id(1).nome("pato branco").uf("PR").build();

	}

}
