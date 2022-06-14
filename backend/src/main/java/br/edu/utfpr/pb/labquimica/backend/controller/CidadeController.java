package br.edu.utfpr.pb.labquimica.backend.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.edu.utfpr.pb.labquimica.backend.model.Cidade;
import br.edu.utfpr.pb.labquimica.backend.service.CidadeService;
import br.edu.utfpr.pb.labquimica.backend.service.CrudService;

@RestController
@RequestMapping("cidade")
public class CidadeController extends CrudController<Cidade, Integer> {

	private CidadeService cidadeService;

	public CidadeController(CidadeService cidadeService) {
		this.cidadeService = cidadeService;

	}

	@Override
	protected CrudService<Cidade, Integer> getService() {
		return cidadeService;
	}

	@GetMapping("busca")
	public List<Cidade> findByNome(@RequestParam("uf") String uf) {

		if (!uf.isEmpty()) {
			return cidadeService.findByUf(uf);
		} else {
			return cidadeService.findAll();
		}
	}
}
