package br.edu.utfpr.pb.labquimica.backend.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.utfpr.pb.labquimica.backend.model.Papel;
import br.edu.utfpr.pb.labquimica.backend.service.CrudService;
import br.edu.utfpr.pb.labquimica.backend.service.PapelService;

@RestController
@RequestMapping("papel")
public class PapelController extends CrudController<Papel, Long> {

	private PapelService papelService;

	public PapelController(PapelService papelService) {
		this.papelService = papelService;
	}

	@Override
	protected CrudService<Papel, Long> getService() {
		return papelService;
	}

}