package br.edu.utfpr.pb.labquimica.backend.controller;

import java.security.Principal;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.utfpr.pb.labquimica.backend.security.acessor.UserAccessor;
import br.edu.utfpr.pb.labquimica.backend.service.UsuarioService;
import br.edu.utfpr.pb.labquimica.backend.viewmodels.DadosPessoaViewModel;
import lombok.NoArgsConstructor;

@RestController
@RequestMapping("session")
@NoArgsConstructor
public class PrincipalController {

	private UserAccessor usuarioAccessor;
	private UsuarioService usuarioService;

	public PrincipalController(UserAccessor usuarioAccessor, UsuarioService usuarioService) {
		this.usuarioAccessor = usuarioAccessor;
		this.usuarioService = usuarioService;
	}

	@GetMapping("user-info")
	public Principal principal(Principal principal) {
		return principal;
	}

	@GetMapping("dados-usuario")
	public DadosPessoaViewModel carregaDados() {
		return usuarioService.carregaDadosUsuario(usuarioAccessor.getUsuarioId());
	}

}
