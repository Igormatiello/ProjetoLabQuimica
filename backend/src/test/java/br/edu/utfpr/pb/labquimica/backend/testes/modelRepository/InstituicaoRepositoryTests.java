package br.edu.utfpr.pb.labquimica.backend.testes.modelRepository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.assertj.core.api.Assertions;
import java.util.Optional;

import javax.persistence.EnumType;

import br.edu.utfpr.pb.labquimica.backend.enumerators.TipoInstituicao;
import br.edu.utfpr.pb.labquimica.backend.model.Cidade;
import br.edu.utfpr.pb.labquimica.backend.model.Equipamento;
import br.edu.utfpr.pb.labquimica.backend.model.Instituicao;
import br.edu.utfpr.pb.labquimica.backend.repository.CidadeRepository;
import br.edu.utfpr.pb.labquimica.backend.repository.InstituicaoRepository;

@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@DataJpaTest
@AutoConfigureTestDatabase


public class InstituicaoRepositoryTests {

	
	@Autowired
	InstituicaoRepository repository;
	
	@Autowired
	TestEntityManager entityManager;
	
	@Test
	public void deveSalvarUmaInstituicao() {
		
		Instituicao instituicao = new Instituicao();
		
		instituicao= repository.save(instituicao);
		Assertions.assertThat(instituicao.getId()).isNotNull();
		
		
		
	}
	
	@Test
	public void deveDeletarUmaInstituicao() {
		
		Instituicao instituicao= criarEPersistirInstituicao();
		repository.delete(instituicao);
		
		Instituicao instituicaoDeletada = entityManager.find(Instituicao.class,instituicao.getId() );
		
		
		Assertions.assertThat(instituicaoDeletada).isNull();	
	}
	
	@Test
	public void deveBuscarUmaInstituicao() {
		Instituicao instituicao= criarEPersistirInstituicao();
		
		java.util.Optional<Instituicao> instituicaoEncontrada= repository.findById(instituicao.getId());
		
		Assertions.assertThat(instituicaoEncontrada.isPresent()).isNotNull();
		
		
	}
	
	
	
	

	
	@Test
	public void deveAtualizarUmaInstituicao() {
		
		Instituicao instituicao = criarEPersistirInstituicao();
	
				instituicao.setId(2);
				instituicao.setNome("utfpr new");
				instituicao.setTipoInstituicao(TipoInstituicao.Externa);
				Cidade cidade = new Cidade();
				cidade.setId(21);
				cidade.setNome("pato branco");
				cidade.setUf("pr");
				
				
				
				instituicao.setCidade(cidade);
				
		
		
		
		
		repository.save(instituicao);
		
		
		
		Instituicao instituicaoNova = entityManager.find(Instituicao.class, instituicao.getId());
		
		
		Assertions.assertThat(instituicaoNova.getNome()).isEqualTo("utfpr new");
		Assertions.assertThat(instituicaoNova.getTipoInstituicao()).isEqualTo(TipoInstituicao.Externa);
		Assertions.assertThat(instituicaoNova.getId()).isEqualTo(2);
		Assertions.assertThat(instituicaoNova.getCidade().getId()).isEqualTo(21);
		Assertions.assertThat(instituicaoNova.getCidade().getNome()).isEqualTo("pato branco");
		Assertions.assertThat(instituicaoNova.getCidade().getUf()).isEqualTo("pr");
	
	}
	
	
	
	
	private Instituicao criarEPersistirInstituicao() {
		
		Instituicao instituicao= criarInstituicao();
		entityManager.persist(instituicao);
		return instituicao;
		
	}
	
	
	
	
	
	public static Instituicao criarInstituicao() {
		
		Cidade cidade = new Cidade();
		cidade.setNome("cidade padrão");
		cidade.setUf("uf padrão");
		cidade.setId(1);
		
		
		return Instituicao
				.builder()
				.nome("Utfpr")
				.cidade(cidade)
				.id(1)
				.tipoInstituicao(TipoInstituicao.Interna)
				.build();
		
		
			
		
		
		
	}
}
