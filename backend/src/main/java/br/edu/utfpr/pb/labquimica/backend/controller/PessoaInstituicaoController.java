package br.edu.utfpr.pb.labquimica.backend.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.utfpr.pb.labquimica.backend.model.ParticipacaoProgramaEnsino;
import br.edu.utfpr.pb.labquimica.backend.model.PessoaInstituicao;
import br.edu.utfpr.pb.labquimica.backend.security.acessor.UserAccessor;
import br.edu.utfpr.pb.labquimica.backend.service.CrudService;
import br.edu.utfpr.pb.labquimica.backend.service.ParticipacaoProgramaEnsinoService;
import br.edu.utfpr.pb.labquimica.backend.service.PessoaInstituicaoService;
import lombok.NoArgsConstructor;

@RestController
@RequestMapping("pessoa-instituicao")
@NoArgsConstructor
public class PessoaInstituicaoController extends CrudController<PessoaInstituicao, Long> {

	private PessoaInstituicaoService pessoaInstituicaoService;

	private UserAccessor usuarioAccessor;

	private ParticipacaoProgramaEnsinoService participacaoProgramaEnsinoService;

	public PessoaInstituicaoController(PessoaInstituicaoService pessoaInstituicaoService, UserAccessor usuarioAccessor,
			ParticipacaoProgramaEnsinoService participacaoProgramaEnsinoService) {
		this.pessoaInstituicaoService = pessoaInstituicaoService;
		this.usuarioAccessor = usuarioAccessor;
		this.participacaoProgramaEnsinoService = participacaoProgramaEnsinoService;

	}

	@Override
	protected CrudService<PessoaInstituicao, Long> getService() {
		return pessoaInstituicaoService;
	}

	@GetMapping("instituicao/{instituicaoId}")
	public List<PessoaInstituicao> findByInstituicao(@PathVariable Integer instituicaoId) {
		return pessoaInstituicaoService.findPessoaInstituicao(usuarioAccessor.getUsuarioId(), instituicaoId);
	}

	@GetMapping("instituicao-sem-usuario/{instituicaoId}")
	public List<PessoaInstituicao> findByInstituicaoSemUsuario(@PathVariable Integer instituicaoId) {
		return pessoaInstituicaoService.findByInstituicao(instituicaoId);
	}

	@GetMapping("instituicoes/{pessoaId}")
	public List<PessoaInstituicao> findByPessoaId(@PathVariable Long pessoaId) {
		return pessoaInstituicaoService.findByPessoaId(pessoaId);
	}

	@Override
	@DeleteMapping("{id}")
	public void delete(@PathVariable Long id) {
		for (ParticipacaoProgramaEnsino programa : participacaoProgramaEnsinoService.findParticipacaoPrograma(id)) {
			participacaoProgramaEnsinoService.delete(programa);
		}
		try {
			getService().delete(id);
		} catch (Exception ex) {

		}

	}
}
