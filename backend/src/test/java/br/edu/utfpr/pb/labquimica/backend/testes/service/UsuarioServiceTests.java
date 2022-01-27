package br.edu.utfpr.pb.labquimica.backend.testes.service;

import static org.assertj.core.api.Assertions.assertThat;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.google.common.base.Optional;

import br.edu.utfpr.pb.labquimica.backend.model.Usuario;
import br.edu.utfpr.pb.labquimica.backend.repository.UsuarioRepository;
import br.edu.utfpr.pb.labquimica.backend.service.UsuarioService;
import br.edu.utfpr.pb.labquimica.backend.service.impl.UsuarioServiceImpl;
import br.edu.utfpr.pb.labquimica.backend.utils.ValidationMessages;
import br.edu.utfpr.pb.labquimica.backend.viewmodels.DadosPessoaViewModel;


@RunWith(SpringRunner.class)
@ActiveProfiles("test")
public class UsuarioServiceTests {

	@SpyBean
	UsuarioServiceImpl service;
	
	@MockBean
	UsuarioRepository repository;
	

	
	
	@Test
	public void deveBuscarUmUsuarioPorString(String s) {
		
		
		Mockito.doNothing().when(service).loadUserByUsername(Mockito.anyString());
		
		
		//CENARIO
		 String username="usuario1";
		 
		 Usuario usuario= Usuario.builder().
					 username("usuario1")
					.password("senha1")
					.build();
		
		 Mockito.doReturn(usuario).when(service).loadUserByUsername(username);

		 //AÇÃO
		 UserDetails  usuarioSalvo= service.loadUserByUsername(username);
		 
		 
		 //VERIFICAR
		 
		 Assertions.assertThat(usuarioSalvo.getUsername()).isEqualTo(username);
		 Assertions.assertThat(usuarioSalvo).isNotNull();
		 
	}
	
	@Test
	public void deveLancarUmErroQuandoNaoBaterUsername(String s) {
		
		//cenario
				String username="usuario1";
				Usuario usuario = Usuario.builder().username("usuario2").password("senha").build();
				Mockito.when(repository.findByUsername(Mockito.anyString())).thenReturn(java.util.Optional.empty());
				
				//ação
				 Throwable exception = Assertions.catchThrowable ( () -> service.loadUserByUsername("usuario1"));
				Assertions.assertThat(exception).isInstanceOf(ValidationMessages.class).hasMessage("O campo \"username\" não pode ser vazio");
	
	}
	
	@Test
	public void deveBuscarUmUsuarioPorId(Long usuarioId) {
		
		
		Mockito.doNothing().when(service).carregaDadosUsuario(Mockito.anyLong());
		
		
		//CENARIO
		 Long id=1l;
		 
		 Usuario usuario= Usuario.builder().
					 username("usuario1")
					.password("senha1")
					.id(1l)
					.build();
		
		 Mockito.doReturn(usuario).when(service).carregaDadosUsuario(id);

		 //AÇÃO
		 DadosPessoaViewModel  usuarioBuscado= service.carregaDadosUsuario(id);
		 
		 
		 //VERIFICAR
		 
	
		 Assertions.assertThat(usuarioBuscado).isNotNull();
		 
	}
	
	@Test
	public void deveLancarErroAoBuscarUmIdVazio() {
		
		
		Mockito.doNothing().when(service).carregaDadosUsuario(Mockito.anyLong());
		
		
		//CENARIO
		 Long id=null;
		 
		 Usuario usuario= Usuario.builder().
					 username(null)
					.password(null)
					.id(1l)
					.build();
		
		 Mockito.doReturn(usuario).when(service).carregaDadosUsuario(id);
		 
		 
		 //VERIFICAR
		 
		 Throwable exception = Assertions.catchThrowable ( () -> service.carregaDadosUsuario(null));
			Assertions.assertThat(exception).isInstanceOf(ValidationMessages.class).hasMessage("O campo \\\"id\\\" não pode ser vazio");
		 
	}
	@Test
	public void deveLancarErroAoBuscarUmUsernameVazio() {
		
		
		Mockito.doNothing().when(service).loadUserByUsername(Mockito.anyString());
		
		String username= null;
		String senha = null;
		
		Usuario usuario= Usuario.builder()
						.username(username)
						.password(senha)
						.id(1l)
						.build();
		Mockito.doReturn(usuario).when(service).loadUserByUsername(username);
		
		 Throwable exception = Assertions.catchThrowable ( () -> service.loadUserByUsername(username));
			Assertions.assertThat(exception).isInstanceOf(ValidationMessages.class).hasMessage("O campo \"username\" não pode ser vazio");
		
		
		 
	}
	
	
	
	}
	
	
	
	
	

