package br.edu.utfpr.pb.labquimica.backend.controller;

import br.edu.utfpr.pb.labquimica.backend.model.Instituicao;
import br.edu.utfpr.pb.labquimica.backend.service.CrudService;
import br.edu.utfpr.pb.labquimica.backend.service.HomeService;
import br.edu.utfpr.pb.labquimica.backend.service.InstituicaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("instituicao")
public class InstituicaoController extends CrudController<Instituicao, Integer> {

	private InstituicaoService instituicaoService;

	public InstituicaoController(InstituicaoService instituicaoService) {
		this.instituicaoService = instituicaoService;
	}

	@Override
	protected CrudService<Instituicao, Integer> getService() {
		return instituicaoService;
	}
}
