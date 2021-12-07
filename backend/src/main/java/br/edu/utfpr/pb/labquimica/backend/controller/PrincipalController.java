package br.edu.utfpr.pb.labquimica.backend.controller;
import br.edu.utfpr.pb.labquimica.backend.security.acessor.UserAccessor;
import br.edu.utfpr.pb.labquimica.backend.service.UsuarioService;
import br.edu.utfpr.pb.labquimica.backend.viewmodels.DadosPessoaViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("session")
public class PrincipalController {

	@Autowired
	private UserAccessor usuarioAccessor;

	@Autowired
	private UsuarioService usuarioService;

	@GetMapping("user-info")
	public Principal principal(Principal principal) {
		return principal;
	}

	@GetMapping("dados-usuario")
	public DadosPessoaViewModel carregaDados(){
		return usuarioService.carregaDadosUsuario(usuarioAccessor.getUsuarioId());
	}

}
