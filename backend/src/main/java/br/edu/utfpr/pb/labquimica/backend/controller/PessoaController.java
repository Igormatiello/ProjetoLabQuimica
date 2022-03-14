package br.edu.utfpr.pb.labquimica.backend.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.utfpr.pb.labquimica.backend.enumerators.TipoPessoa;
import br.edu.utfpr.pb.labquimica.backend.model.Pessoa;
import br.edu.utfpr.pb.labquimica.backend.repository.PessoaRepository;
import br.edu.utfpr.pb.labquimica.backend.security.acessor.UserAccessor;
import br.edu.utfpr.pb.labquimica.backend.service.CrudService;
import br.edu.utfpr.pb.labquimica.backend.service.PessoaService;
import br.edu.utfpr.pb.labquimica.backend.viewmodels.ResultadoOperacaoViewModel;
import br.edu.utfpr.pb.labquimica.backend.viewmodels.SolicitacaoCadastroViewModel;
import lombok.NoArgsConstructor;

@RestController
@RequestMapping("pessoa")
@NoArgsConstructor
public class PessoaController extends CrudController<Pessoa, Long> {

	private PessoaService pessoaService;

	private PessoaRepository pessoaRepository;

	private UserAccessor usuarioAccessor;

	public PessoaController(PessoaService pessoaService, PessoaRepository pessoaRepository,
			UserAccessor usuarioAccessor) {
		this.pessoaService = pessoaService;
		this.pessoaRepository = pessoaRepository;
		this.usuarioAccessor = usuarioAccessor;

	}

	@Override
	protected CrudService<Pessoa, Long> getService() {
		return pessoaService;
	}

	@GetMapping("dados-aprovacao/{id}")
	public SolicitacaoCadastroViewModel getDadosAprovacao(@PathVariable Long id) {
		return pessoaService.getDadosValidacaoCadastro(id);
	}

	@GetMapping("doc-exists/{tipoPessoa}/{cpfCnpj}")
	public ResultadoOperacaoViewModel<Object> existsComDocumento(@PathVariable TipoPessoa tipoPessoa,
			@PathVariable String cpfCnpj) {
		return pessoaService.findCpfCnpjCadastrado(tipoPessoa, cpfCnpj);
	}

	@PostMapping("aprovar-solicitacao")
	public ResultadoOperacaoViewModel<Pessoa> saveAprovacao(@RequestBody @Valid SolicitacaoCadastroViewModel source) {
		var pessoa = pessoaService.saveValidarCadastro(source);
		if (pessoa.isSucesso()) {
			pessoaService.sendEmailRetornoSolicitacao(source.getEmail());
		}
		return pessoa;
	}

	@GetMapping("buscaProfessor")
	public List<Pessoa> buscaProfessores() {
		return pessoaRepository.buscaProfessores();
	}

	@GetMapping("buscaProfessorFormulario/{pessoaInstituicaoId}")
	public List<Pessoa> buscaProfessoresFormulario(@PathVariable Long pessoaInstituicaoId) {
		return pessoaRepository.buscaProfessoresFormularios(pessoaInstituicaoId);
	}

	@GetMapping("buscaPessoaIdProfessorLogado")
	public List<Pessoa> buscaPessoaIdProfessorLogado() {
		return pessoaRepository.buscaProfessorByUsuarioId(usuarioAccessor.getUsuarioId());
	}

}
