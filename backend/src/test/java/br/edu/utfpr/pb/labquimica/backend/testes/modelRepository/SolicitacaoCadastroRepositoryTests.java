package br.edu.utfpr.pb.labquimica.backend.testes.modelRepository;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;

import org.assertj.core.api.Assertions;
import org.assertj.core.api.LocalDateAssert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import br.edu.utfpr.pb.labquimica.backend.enumerators.TipoPessoa;
import br.edu.utfpr.pb.labquimica.backend.model.Cidade;
import br.edu.utfpr.pb.labquimica.backend.model.Equipamento;
import br.edu.utfpr.pb.labquimica.backend.model.Instituicao;
import br.edu.utfpr.pb.labquimica.backend.model.Pessoa;
import br.edu.utfpr.pb.labquimica.backend.model.SolicitacaoCadastro;
import br.edu.utfpr.pb.labquimica.backend.repository.CidadeRepository;
import br.edu.utfpr.pb.labquimica.backend.repository.SolicitacaoCadastroRepository;

@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@DataJpaTest
@AutoConfigureTestDatabase


public class SolicitacaoCadastroRepositoryTests {

	
	@Autowired
	
	SolicitacaoCadastroRepository repository;
	
	@Autowired
	TestEntityManager entityManager;
	
	
	@Test
public void deveSalvarUmaSolicitacaoCadastro() {
		
		SolicitacaoCadastro solicitacaoCadastro= criarEPersistirSolicitacaoCadastro();
		
		SolicitacaoCadastro solicitacaoCadastroSalvo= repository.save(solicitacaoCadastro);
		
		Assertions.assertThat(solicitacaoCadastroSalvo.getId()).isNotNull();
		
	}
	
	@Test
	public void deveDeletarUmaSolicitacaoCadastro() {
		
		SolicitacaoCadastro solicitacaoCadastro= criarEPersistirSolicitacaoCadastro();
		
		solicitacaoCadastro= entityManager.find(SolicitacaoCadastro.class, solicitacaoCadastro.getId());
		
		repository.delete(solicitacaoCadastro);
		
		SolicitacaoCadastro solicatacaoCadastroDeletada = entityManager.find(SolicitacaoCadastro.class,solicitacaoCadastro.getId() );
		
		
		Assertions.assertThat(solicatacaoCadastroDeletada).isNull();	
	}
	
	@Test
	public void deveBuscarUmaSolicitacaoCadastro() {
		SolicitacaoCadastro solicitacaoCadastro= criarEPersistirSolicitacaoCadastro();
		
		
		
		java.util.Optional<SolicitacaoCadastro> solicitacaoCadastroEncontrada= repository.findById(solicitacaoCadastro.getId());
		
		
		Assertions.assertThat(solicitacaoCadastroEncontrada.isPresent()).isNotNull();
		
		
	}
	
	
	
	

	
	@Test
	public void deveAtualizarUmaSolicitacaoCadastro() {
		
		SolicitacaoCadastro solicitacaoCadastro = criarEPersistirSolicitacaoCadastro();
		
		Cidade cidade = new Cidade();
		cidade.setNome("xaxim");
		cidade.setUf("SC");
		
				solicitacaoCadastro.setSenha("234");
				solicitacaoCadastro.setTipoPessoa(TipoPessoa.Juridica);
				solicitacaoCadastro.setCidade(cidade);
				solicitacaoCadastro.setTelefone("11");
				solicitacaoCadastro.setCelular("11");
				solicitacaoCadastro.getDataCriacao();
				solicitacaoCadastro.setNomeInstituicao("ifpr");
				solicitacaoCadastro.setCpfOrientador("101010");
				
	
		repository.save(solicitacaoCadastro);
		
		
		
		SolicitacaoCadastro solicitacaoCadastroNovo = entityManager.find(SolicitacaoCadastro.class, solicitacaoCadastro.getId());
		
		
		assertThat(solicitacaoCadastroNovo.getSenha()).isEqualTo("234");
		assertThat(solicitacaoCadastroNovo.getTipoPessoa()).isEqualTo(TipoPessoa.Fisica);
		assertThat(solicitacaoCadastroNovo.getCidade().getNome()).isEqualTo("xaxim");
		assertThat(solicitacaoCadastroNovo.getCidade().getUf()).isEqualTo("SC");
		assertThat(solicitacaoCadastroNovo.getTelefone()).isEqualTo("11");
		assertThat(solicitacaoCadastroNovo.getCelular()).isEqualTo("11");
		assertThat(solicitacaoCadastroNovo.getNomeInstituicao()).isEqualTo("ifpr");
		assertThat(solicitacaoCadastroNovo.getCpfOrientador()).isEqualTo("101010");

		
	}
	
	
	
	
	private SolicitacaoCadastro criarEPersistirSolicitacaoCadastro() {
		
		SolicitacaoCadastro solicitacaoCadastro= criarSolicitacaoCadastro();
		entityManager.persist(solicitacaoCadastro);
		return solicitacaoCadastro;
		
	}
	
	
	
	
	
	public static SolicitacaoCadastro criarSolicitacaoCadastro() {
		
		Cidade cidade = new Cidade();
		cidade.setNome("pato branco");
		cidade.setUf("PR");
		
		
		
		Pessoa pessoa = new Pessoa();
		pessoa.setDocumento("novo documento");
		pessoa.setBairro("norte");
		pessoa.setNome("igor matiello");
		
		return SolicitacaoCadastro
				.builder()
				.senha("123")
				.tipoPessoa(TipoPessoa.Fisica)
				.cidade(cidade)
				.nome("igor")
				.documento("100")
				.cep("8986000")
				.endereco("rua dos marajos")
				.bairro("centro")
				.numero("877")
				.complemento("ed solymar")
				.nomeprojeto("analise quimica peixe")
				.telefone("898989")
				.celular("898989")
				.email("igor@email.com")
				.dataCriacao(LocalDate.now())
				.nomeInstituicao("utfpr")
				.cpfOrientador("1010")
				.nomeProgramaEnsino("nome")
				.ehProfessor(false)
				.dataTerminoProgramaEnsino(LocalDate.of(2022, 10, 4))
				.inscricaoEstadual("ie")
				.pessoa(pessoa)
				.build();
				
		
	}
}
