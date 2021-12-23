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
		//cenario
		Usuario usuario = criarEPersistirUmUsuario();
					
		//ação
		
		Usuario usuarioSalvo = repository.save(usuario);
	
		//verificação
		
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

	repository.save(usuario);
	

	Usuario usuarioAtualizado = entityManager.find(Usuario.class, usuario.getId());
	
	
	Assertions.assertThat(usuarioAtualizado.getPassword()).isEqualTo("senha atual");
	Assertions.assertThat(usuarioAtualizado.getUsername()).isEqualTo("usuario atual");
	Assertions.assertThat(usuarioAtualizado.getPessoa().getCep()).isEqualTo("22222");
	Assertions.assertThat(usuarioAtualizado.getPessoa().getNome()).isEqualTo("igor");
	Assertions.assertThat(usuarioAtualizado.getPessoa().getEndereco()).isEqualTo("new ed");
	
	
}


@Test
public void deveBuscarUmUsuarioPorId() {
	
	
	Usuario usuario = criarEPersistirUmUsuario();
	java.util.Optional<Usuario> lancamentoEncontrado= repository.findById(usuario.getId());
			
	assertThat(lancamentoEncontrado.isPresent()).isTrue();

}



	
private Usuario criarEPersistirUmUsuario() {
	Usuario usuario = criarUsuario();
	entityManager.persist(usuario);
	return usuario;
}
	
	public static Usuario criarUsuario() {
		
		List<PessoaInstituicao> listaVinculos = new ArrayList<>();
		
		Papel papeis = new Papel();
		Set <Papel> papeis= new ArrayList<>();
		
		papeis=new HashSet<Papel>();
		
		
		
		
		
		
		Pessoa pessoa = new Pessoa();
		pessoa.setId(1L);
		pessoa.setNome("usuario1");
		pessoa.setDocumento("documento 1");
		pessoa.setCep("11111111");
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
		
		
		
		
		return Usuario
				.builder()
				.username("usuario1")
				.password("senha1")
				.enabled(Boolean.TRUE)
				.papeis(papeis)
				.pessoa(pessoa)
				.build();
								
	}
	
}