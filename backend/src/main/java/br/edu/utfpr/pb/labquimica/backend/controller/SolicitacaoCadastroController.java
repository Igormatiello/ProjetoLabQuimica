package br.edu.utfpr.pb.labquimica.backend.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.utfpr.pb.labquimica.backend.exceptions.NotImplementedException;
import br.edu.utfpr.pb.labquimica.backend.model.SolicitacaoCadastro;
import br.edu.utfpr.pb.labquimica.backend.service.CrudService;
import br.edu.utfpr.pb.labquimica.backend.service.SolicitacaoCadastroService;
import br.edu.utfpr.pb.labquimica.backend.viewmodels.ResultadoOperacaoViewModel;
import lombok.NoArgsConstructor;

@RestController
@RequestMapping("solicitacao-cadastro")
@NoArgsConstructor
public class SolicitacaoCadastroController extends CrudController<SolicitacaoCadastro, Long> {

	private SolicitacaoCadastroService solicitacaoCadastroService;

	public SolicitacaoCadastroController(SolicitacaoCadastroService solicitacaoCadastroService) {
		this.solicitacaoCadastroService = solicitacaoCadastroService;
	}

	@Override
	protected CrudService<SolicitacaoCadastro, Long> getService() {
		return solicitacaoCadastroService;
	}

	@Override
	public SolicitacaoCadastro save(@RequestBody SolicitacaoCadastro entity) {
		throw new NotImplementedException();
	}

	@PostMapping("new")
	public ResultadoOperacaoViewModel<SolicitacaoCadastro> saveAprovacao(
			@RequestBody @Valid SolicitacaoCadastro source) {
		var newSolicitacao = solicitacaoCadastroService.saveWithValidation(source);
		if (newSolicitacao.isSucesso()) {
			solicitacaoCadastroService.sendEmailToResponsavelLiberacao();
		}
		return solicitacaoCadastroService.saveWithValidation(source);
	}

	@Override
	public List<SolicitacaoCadastro> findAll() {
		return solicitacaoCadastroService.findSolicitacoesAbertas();
	}
}
