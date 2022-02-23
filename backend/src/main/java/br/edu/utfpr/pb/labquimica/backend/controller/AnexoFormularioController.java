package br.edu.utfpr.pb.labquimica.backend.controller;

import br.edu.utfpr.pb.labquimica.backend.model.AnexoFormulario;
import br.edu.utfpr.pb.labquimica.backend.model.Formulario;
import br.edu.utfpr.pb.labquimica.backend.repository.AnexoFormularioRepository;
import br.edu.utfpr.pb.labquimica.backend.service.AnexoFormularioService;
import br.edu.utfpr.pb.labquimica.backend.service.CrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
