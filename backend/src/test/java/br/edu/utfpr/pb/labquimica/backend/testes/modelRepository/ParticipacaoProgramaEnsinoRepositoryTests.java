package br.edu.utfpr.pb.labquimica.backend.testes.modelRepository;

import static org.assertj.core.api.Assertions.assertThat;

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
import br.edu.utfpr.pb.labquimica.backend.model.ParticipacaoProgramaEnsino;
import br.edu.utfpr.pb.labquimica.backend.model.Pessoa;
import br.edu.utfpr.pb.labquimica.backend.model.PessoaInstituicao;
import br.edu.utfpr.pb.labquimica.backend.repository.CidadeRepository;
import br.edu.utfpr.pb.labquimica.backend.repository.ParticipacaoProgramaEnsinoRepository;

@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@DataJpaTest
@AutoConfigureTestDatabase


public class ParticipacaoProgramaEnsinoRepositoryTests {

	@Autowired
	ParticipacaoProgramaEnsinoRepository repository;
	
	@Autowired
	TestEntityManager entityManager;
	
@Test
	
	public void deveSalvarUmaPaticipacaoProgramaEnsinoNaBaseDeDados() {

		ParticipacaoProgramaEnsino participacaoProgramaEnsino= criarEPersistirParticipacaoProgramaEnsino();
					
		
		ParticipacaoProgramaEnsino ppeSalvo = repository.save(participacaoProgramaEnsino);
	
		
		Assertions.assertThat(ppeSalvo.getId()).isNotNull();
	}

@Test
public void deveDeletarUmaParticipacaoProgramaEnsino() {
	ParticipacaoProgramaEnsino participacaoProgramaEnsino = criarEPersistirParticipacaoProgramaEnsino();
	
	participacaoProgramaEnsino = entityManager.find(ParticipacaoProgramaEnsino.class, participacaoProgramaEnsino.getId());
	
	repository.delete(participacaoProgramaEnsino);
	
	
	
	ParticipacaoProgramaEnsino participacaoProgramaEnsinoInexistente = entityManager.find(ParticipacaoProgramaEnsino.class, participacaoProgramaEnsino.getId());
	assertThat(participacaoProgramaEnsinoInexistente).isNull();
}
	
@Test
public void deveAtualizarUmaParticipacaoProgramaEnsino() {
	ParticipacaoProgramaEnsino participacaoProgramaEnsino = criarEPersistirParticipacaoProgramaEnsino();
	

	
	repository.save(participacaoProgramaEnsino);
	
	ParticipacaoProgramaEnsino pgeAtualizado = entityManager.find(ParticipacaoProgramaEnsino.class, participacaoProgramaEnsino.getId());
	
	//ASSERTIONS
	
	
}


@Test
public void deveBuscarUmaParticipacaoProgramaEnsinoInstituicaoPorId() {
	
	
	ParticipacaoProgramaEnsino participacaoProgramaEnsino = criarEPersistirParticipacaoProgramaEnsino();
	java.util.Optional<ParticipacaoProgramaEnsino> pIEncontrado= repository.findById(participacaoProgramaEnsino.getId());
			
	assertThat(pIEncontrado.isPresent()).isTrue();

}
	
	

	
	
	private ParticipacaoProgramaEnsino criarEPersistirParticipacaoProgramaEnsino() {
		ParticipacaoProgramaEnsino participacaoProgramaEnsino = criarParticipacaoProgramaEnsino();
		entityManager.persist(participacaoProgramaEnsino);
		return participacaoProgramaEnsino;
	}
		
		public static ParticipacaoProgramaEnsino criarParticipacaoProgramaEnsino() {
			
	
			
			return ParticipacaoProgramaEnsino
					.builder()
					.programaEnsino(null)
					.participante(null)
					.orientador(null)
					.dataTermino(null)
					.ehAtivo(false)
					.build();
									
		}
	
	
	
	
}
