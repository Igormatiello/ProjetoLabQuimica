package br.edu.utfpr.pb.labquimica.backend.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.utfpr.pb.labquimica.backend.model.AnexoFormulario;
import br.edu.utfpr.pb.labquimica.backend.service.AnexoFormularioService;
import br.edu.utfpr.pb.labquimica.backend.service.CrudService;

@RestController
@RequestMapping("anexo-formulario")
public class AnexoFormularioController extends CrudController<AnexoFormulario, Long> {

	private AnexoFormularioService anexoFormularioService;

	public AnexoFormularioController(AnexoFormularioService anexoFormularioService) {
		this.anexoFormularioService = anexoFormularioService;

	}

	@Override
	protected CrudService<AnexoFormulario, Long> getService() {
		return anexoFormularioService;
	}
}
