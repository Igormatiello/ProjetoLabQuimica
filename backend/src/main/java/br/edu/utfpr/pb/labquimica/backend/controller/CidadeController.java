package br.edu.utfpr.pb.labquimica.backend.controller;

import br.edu.utfpr.pb.labquimica.backend.model.Cidade;
import br.edu.utfpr.pb.labquimica.backend.service.CidadeService;
import br.edu.utfpr.pb.labquimica.backend.service.CrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

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
	public List<Cidade> findByNome(@RequestParam("nome") Optional<String> nome) {
		if (!nome.isEmpty()) {
			return cidadeService.findByNomeContainingIgnoreCaseOrderByNomeAsc(nome.get());
		} else {
			return cidadeService.findAll();
		}
	}
}
