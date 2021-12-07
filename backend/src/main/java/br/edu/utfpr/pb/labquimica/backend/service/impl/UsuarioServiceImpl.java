package br.edu.utfpr.pb.labquimica.backend.service.impl;

import br.edu.utfpr.pb.labquimica.backend.model.Pessoa;
import br.edu.utfpr.pb.labquimica.backend.model.Usuario;
import br.edu.utfpr.pb.labquimica.backend.repository.PessoaInstituicaoRepository;
import br.edu.utfpr.pb.labquimica.backend.repository.PessoaRepository;
import br.edu.utfpr.pb.labquimica.backend.repository.UsuarioRepository;
import br.edu.utfpr.pb.labquimica.backend.service.UsuarioService;
import br.edu.utfpr.pb.labquimica.backend.viewmodels.DadosPessoaViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UsuarioServiceImpl extends CrudServiceImpl<Usuario, Long> implements UsuarioService {

	@Autowired
	private UsuarioRepository userRepository;

	@Autowired
	private PessoaRepository pessoaRepository;

	@Autowired
	private PessoaInstituicaoRepository pessoaInstituicaoRepository;
	
	@Override
	protected JpaRepository<Usuario, Long> getRepository() {
		return userRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
		return userRepository.findByUsername(s).orElseThrow(
				() -> new UsernameNotFoundException("Usuario não encontrado!")
		);
	}

	@Override
	public DadosPessoaViewModel carregaDadosUsuario(Long usuarioId) {
		var dados = new DadosPessoaViewModel();
		var pessoaInstituicao = pessoaInstituicaoRepository.findPessoaInstituicao(usuarioId);
		pessoaInstituicao.forEach( p -> p.setPessoa(null));
		var pessoa = pessoaRepository.findByUsuarioId(usuarioId);

		dados.setPessoaId(pessoa.getId());
		dados.setTipoPessoa(pessoa.getTipoPessoa());
		dados.setNome(pessoa.getNome());
		dados.setDocumento(pessoa.getDocumento());
		dados.setCep(pessoa.getCep());
		dados.setEndereco(pessoa.getEndereco());
		dados.setBairro(pessoa.getBairro());
		dados.setNumero(pessoa.getNumero());
		dados.setComplemento(pessoa.getComplemento());
		dados.setTelefone(pessoa.getTelefone());
		dados.setCelular(pessoa.getCelular());
		dados.setEmail(pessoa.getEmail());
		dados.setInscricaoEstadual(pessoa.getInscricaoEstadual());
		dados.setDataCriacao(pessoa.getDataCriacao());
		dados.setEhAtivo(pessoa.isEhAtivo());
		dados.setCidade(pessoa.getCidade());
		dados.setUsuario(pessoa.getUsuario());
		dados.setPessoaInstituicoes(pessoaInstituicao);
		return dados;
	}
}
