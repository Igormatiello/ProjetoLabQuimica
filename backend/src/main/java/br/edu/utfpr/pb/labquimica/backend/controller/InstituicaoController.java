package br.edu.utfpr.pb.labquimica.backend.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.utfpr.pb.labquimica.backend.model.Instituicao;
import br.edu.utfpr.pb.labquimica.backend.service.CrudService;
import br.edu.utfpr.pb.labquimica.backend.service.InstituicaoService;

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
