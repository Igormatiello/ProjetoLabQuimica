package br.edu.utfpr.pb.labquimica.backend.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.utfpr.pb.labquimica.backend.model.CreditoProfessor;
import br.edu.utfpr.pb.labquimica.backend.repository.CreditoProfessorRepository;
import br.edu.utfpr.pb.labquimica.backend.service.CreditoProfessorService;
import br.edu.utfpr.pb.labquimica.backend.service.CrudService;
import br.edu.utfpr.pb.labquimica.backend.viewmodels.ResultadoOperacaoViewModel;
import lombok.NoArgsConstructor;

@RestController
@RequestMapping("creditoProfessor")
@NoArgsConstructor
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
