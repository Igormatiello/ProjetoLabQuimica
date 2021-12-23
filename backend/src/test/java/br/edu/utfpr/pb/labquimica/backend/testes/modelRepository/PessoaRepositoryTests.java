package br.edu.utfpr.pb.labquimica.backend.testes.modelRepository;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.util.ArrayList;
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

import br.edu.utfpr.pb.labquimica.backend.model.Cidade;
import br.edu.utfpr.pb.labquimica.backend.model.Pessoa;
import br.edu.utfpr.pb.labquimica.backend.model.PessoaInstituicao;
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
		
		Pessoa pessoa= criarPessoa();
		
		Pessoa pessoaSalva= repository.save(pessoa);
		
		Assertions.assertThat(pessoaSalva.getId()).isNotNull();		
		
	}
	
	
	@Test
	public void deverRetornarFalsoQuandoNaoHouverUmaPessoaNaBaseDeDados() {
		
		
		boolean resultado =repository.existsById(1L);
		
		Assertions.assertThat(resultado).isFalse();		
		
	}
	
	@Test
	public void deveBuscarUmaPessoaPorId() {
		
		
		Pessoa pessoa = criarEPersistirUmaPessoa();
		java.util.Optional<Pessoa> pessoaEncontrada= repository.findById(pessoa.getId());
				
		assertThat(pessoaEncontrada.isPresent()).isTrue();

	}
	
	@Test
	public void deveRetornarFalsoAoBuscarUmaPessoaPorIdNaoCadastrada() {
		
		
		boolean resultado= repository.existsById(10L);
				
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
		PessoaInstituicao lista1=new PessoaInstituicao();
		lista1.setId(2L);
		lista1.setEhProfessor(false);
		
		listaVinculos.add(lista1);
		
		PessoaInstituicao lista2=new PessoaInstituicao();
		lista2.setId(3L);
		lista2.setEhProfessor(true);
		
		listaVinculos.add(lista2);
		
		pessoa.setVinculos(listaVinculos);	
		repository.save(pessoa);
		
		Pessoa pessoaAtualizada = entityManager.find(Pessoa.class, pessoa.getId());
		
		assertThat(pessoaAtualizada.getEndereco()).isEqualTo("novo endereço");
		assertThat(pessoaAtualizada.getCelular()).isEqualTo("1212");
		assertThat(pessoaAtualizada.getUsuario().getId()).isEqualTo(4l);
		assertThat(pessoaAtualizada.getVinculos().get(0).getId()).isEqualTo(3L);
		assertThat(pessoaAtualizada.getVinculos().get(0))
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
		
		
		Cidade cidade= new Cidade();
		cidade.setId(1);
		cidade.setNome("xaxim");
		cidade.setUf("SC");
		
		
		List<PessoaInstituicao> listaVinculos = new ArrayList<>();
		PessoaInstituicao lista1=new PessoaInstituicao();
		lista1.setId(1L);
		lista1.setEhProfessor(true);
		
		listaVinculos.add(lista1);
		
		PessoaInstituicao lista2=new PessoaInstituicao();
		lista2.setId(2L);
		lista2.setEhProfessor(false);
		
		listaVinculos.add(lista2);
		
		
		Usuario usuario = new Usuario();
		usuario.setId(1L);
		
		return Pessoa
				.builder()
				.nome("pessoa1")
				.documento("6666")
				.cep("1111")
				.endereco("endereço1")
				.bairro("centro")
				.numero("224")
				.complemento("na avenida")
				.telefone("49999999")
				.celular("499999999")
				.email("pessoa1@email.com")
				.inscricaoEstadual("ie1")
				.dataCriacao(LocalDate.now())
				.ehAtivo(true)
				.cidade(cidade)
				.vinculos(listaVinculos)
				//.solicitacaoCadastro()
				.usuario(usuario)
				.build();
				
				
				
	}
	
	
}
