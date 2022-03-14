package br.edu.utfpr.pb.labquimica.backend.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.utfpr.pb.labquimica.backend.model.ProgramaEnsino;
import br.edu.utfpr.pb.labquimica.backend.service.CrudService;
import br.edu.utfpr.pb.labquimica.backend.service.ProgramaEnsinoService;

@RestController
@RequestMapping("programa-ensino")
public class ProgramaEnsinoController extends CrudController<ProgramaEnsino, Long> {

	private ProgramaEnsinoService programaEnsinoService;

	public ProgramaEnsinoController(ProgramaEnsinoService programaEnsinoService) {
		this.programaEnsinoService = programaEnsinoService;
	}

	@Override
	protected CrudService<ProgramaEnsino, Long> getService() {
		return programaEnsinoService;
	}
}
