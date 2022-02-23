package br.edu.utfpr.pb.labquimica.backend.controller;

import br.edu.utfpr.pb.labquimica.backend.model.Papel;
import br.edu.utfpr.pb.labquimica.backend.model.Usuario;
import br.edu.utfpr.pb.labquimica.backend.repository.LancamentoFinanceiroRepository;
import br.edu.utfpr.pb.labquimica.backend.repository.PapelRepository;
import br.edu.utfpr.pb.labquimica.backend.service.CrudService;
import br.edu.utfpr.pb.labquimica.backend.service.PapelService;
import br.edu.utfpr.pb.labquimica.backend.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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