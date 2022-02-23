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
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.google.common.base.Optional;

import br.edu.utfpr.pb.labquimica.backend.model.Usuario;
import br.edu.utfpr.pb.labquimica.backend.repository.UsuarioRepository;
import br.edu.utfpr.pb.labquimica.backend.service.UsuarioService;
import br.edu.utfpr.pb.labquimica.backend.service.impl.UsuarioServiceImpl;
import br.edu.utfpr.pb.labquimica.backend.testes.modelRepository.CriarObjetos;
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
	public void deveBuscarUmUsuarioPorString() {

		Mockito.doNothing().when(service).loadUserByUsername(Mockito.anyString());

		Usuario usuario = CriarObjetos.criarUsuario();

		String username = usuario.getUsername();

		UserDetails resultado = service.loadUserByUsername(username);

		Assertions.assertThat(resultado.getUsername()).isEqualTo(username);

		Assertions.assertThat(resultado).isEqualTo(usuario);

	}

	@Test
	public void deveLancarUmErroQuandoNaoEncontrarUsername(String s) {

		Mockito.doNothing().when(service).loadUserByUsername(Mockito.anyString());

		String username = "sobrenome";

		UserDetails resultado = service.loadUserByUsername(username);

		Mockito.doThrow(UsernameNotFoundException.class).when(service.loadUserByUsername(username));

		Throwable exception = Assertions.catchThrowable(() -> service.loadUserByUsername(username));
		Assertions.assertThat(exception).isInstanceOf(UsernameNotFoundException.class)
				.hasMessage("Usuario não encontrado!");

	}

	@Test
	public void deveBuscarUmUsuarioPorId() {

		Usuario usuario = CriarObjetos.criarUsuario();

		Long id = usuario.getId();

		DadosPessoaViewModel resultado = service.carregaDadosUsuario(id);

		Assertions.assertThat(resultado).isNotNull();

		Assertions.assertThat(resultado.getCelular()).isEqualTo(usuario.getPessoa().getCelular());

	}

}
