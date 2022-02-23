package br.edu.utfpr.pb.labquimica.backend.controller;

import br.edu.utfpr.pb.labquimica.backend.model.CreditoProfessor;
import br.edu.utfpr.pb.labquimica.backend.model.Pessoa;
import br.edu.utfpr.pb.labquimica.backend.repository.CreditoProfessorRepository;
import br.edu.utfpr.pb.labquimica.backend.service.ConfEmailService;
import br.edu.utfpr.pb.labquimica.backend.service.CreditoProfessorService;
import br.edu.utfpr.pb.labquimica.backend.service.CrudService;
import br.edu.utfpr.pb.labquimica.backend.viewmodels.ResultadoOperacaoViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("creditoProfessor")
public class CreditoProfessorController extends CrudController<CreditoProfessor, Long> {

	private CreditoProfessorService creditoProfessorService;

	public CreditoProfessorController(CreditoProfessorService creditoProfessorService) {
		this.creditoProfessorService = creditoProfessorService;

	}

	private CreditoProfessorRepository creditoProfessorRepository;

	public CreditoProfessorController(CreditoProfessorRepository creditoProfessorRepository) {
		this.creditoProfessorRepository = creditoProfessorRepository;

	}

	@Override
	protected CrudService<CreditoProfessor, Long> getService() {
		return creditoProfessorService;
	}

	@PostMapping("novo")
	public ResultadoOperacaoViewModel<CreditoProfessor> saveAprovacao(@RequestBody @Valid CreditoProfessor source) {
		return creditoProfessorService.saveWithValidation(source);
	}

	@GetMapping("buscaCreditosPorProfessor/{pessoaId}")
	public List<CreditoProfessor> buscaCreditosPorProfessor(@PathVariable Long pessoaId) {
		return creditoProfessorRepository.buscaCreditosPorProfessor(pessoaId);
	}
}
