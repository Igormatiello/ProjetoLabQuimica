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

import br.edu.utfpr.pb.labquimica.backend.model.Cidade;
import br.edu.utfpr.pb.labquimica.backend.model.ProgramaEnsino;
import br.edu.utfpr.pb.labquimica.backend.repository.CidadeRepository;
import br.edu.utfpr.pb.labquimica.backend.repository.ProgramaEnsinoRepository;

@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@DataJpaTest
@AutoConfigureTestDatabase

public class ProgramaEnsinoRepositoryTests {

	
	@Autowired
	ProgramaEnsinoRepository repository;
	
	@Autowired
	TestEntityManager entityManager;
	
	@Test
	public void deveSalvarUmProgramaEnsino() {
		
		ProgramaEnsino programaEnsino= criarProgramaEnsino();
			
		//programaEnsino= repository.save(programaEnsino);
		Assertions.assertThat(programaEnsino.getId()).isNotNull();
		
		
		
	}
	
	@Test
	public void deveDeletarUmProgramaEnsino() {
		
		ProgramaEnsino programaEnsino= criarProgramaEnsino();
		entityManager.persist(programaEnsino);
		programaEnsino= entityManager.find(ProgramaEnsino.class, programaEnsino.getId());
		
		repository.delete(programaEnsino);
		
		ProgramaEnsino programaEnsinoDeletado = entityManager.find(ProgramaEnsino.class, programaEnsino);
		
		
		Assertions.assertThat(programaEnsinoDeletado).isNull();	
	}
	
	@Test
	public void deveBuscarUmProgramEnsino() {
		ProgramaEnsino programaEnsino= criarEPersistirUmProgramaEnsino();
		
		
		
		java.util.Optional<ProgramaEnsino> ProgramaEnsinoEncontrado= repository.findById(programaEnsino.getId());
		
		
		Assertions.assertThat(ProgramaEnsinoEncontrado.isPresent()).isNotNull();
		
		
	}
	
	
	
	

	
	@Test
	public void deveAtualizarUmProgramaEnsino() {
		
		ProgramaEnsino programaEnsino= criarEPersistirUmProgramaEnsino();
		
		programaEnsino.setSigla("TC");
		programaEnsino.setNome("Trabalho Científico");
		
		
		repository.save(programaEnsino);
		
		
		
		ProgramaEnsino programaEnsinoAtualizado = entityManager.find(ProgramaEnsino.class, programaEnsino.getId());
		
		
		Assertions.assertThat(programaEnsino.getNome()).isEqualTo("Trabalho Cientifico");
		Assertions.assertThat(programaEnsino.getSigla()).isEqualTo("TC");
		
	}
	
	
	
	
	private ProgramaEnsino criarEPersistirUmProgramaEnsino() {
		
		ProgramaEnsino programaEnsino= criarProgramaEnsino();
		entityManager.persist(programaEnsino);
		return programaEnsino;
		
	}
	
	
	
	
	public static ProgramaEnsino criarProgramaEnsino() {
		
		return
				ProgramaEnsino
				.builder()
				.nome("Trabalho de Conclusão de Curso")
				.sigla("TCC")
				.build();
		
	}
	
	

}
