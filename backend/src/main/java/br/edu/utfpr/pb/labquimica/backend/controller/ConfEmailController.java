package br.edu.utfpr.pb.labquimica.backend.controller;

import br.edu.utfpr.pb.labquimica.backend.model.ConfEmail;
import br.edu.utfpr.pb.labquimica.backend.service.CidadeService;
import br.edu.utfpr.pb.labquimica.backend.service.ConfEmailService;
import br.edu.utfpr.pb.labquimica.backend.service.CrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("conf-email")
public class ConfEmailController extends CrudController<ConfEmail, Integer> {

	private ConfEmailService confEmailService;

	public ConfEmailController(ConfEmailService confEmailService) {
		this.confEmailService = confEmailService;

	}

	@Override
	protected CrudService<ConfEmail, Integer> getService() {
		return confEmailService;
	}

	@GetMapping("find-conf-geral")
	public ConfEmail findConfEmail() {
		return confEmailService.findConfig();
	}

	@GetMapping("teste-envio")
	public void testeEnvio() {
		confEmailService.sendEmailTeste();
	}
}
