package br.edu.utfpr.pb.labquimica.backend.service;

import br.edu.utfpr.pb.labquimica.backend.model.Usuario;
import br.edu.utfpr.pb.labquimica.backend.viewmodels.DadosPessoaViewModel;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UsuarioService extends CrudService<Usuario, Long>, UserDetailsService {

    DadosPessoaViewModel carregaDadosUsuario(Long usuarioId);
}
