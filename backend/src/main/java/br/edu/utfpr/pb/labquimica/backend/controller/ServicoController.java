package br.edu.utfpr.pb.labquimica.backend.controller;

import br.edu.utfpr.pb.labquimica.backend.model.Cidade;
import br.edu.utfpr.pb.labquimica.backend.model.Servico;
import br.edu.utfpr.pb.labquimica.backend.service.CrudService;
import br.edu.utfpr.pb.labquimica.backend.service.ProgramaEnsinoService;
import br.edu.utfpr.pb.labquimica.backend.service.ServicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("servico")
public class ServicoController extends CrudController<Servico, Integer> {

	private ServicoService servicoService;

	public ServicoController(ServicoService servicoService) {
		this.servicoService = servicoService;
	}

	@Override
	protected CrudService<Servico, Integer> getService() {
		return servicoService;
	}

	@GetMapping("equipamento")
	public List<Servico> findAllByEquipamentoIdOrderByDescricao(@RequestParam("equipamento") Integer equipamentoId) {
		return servicoService.findAllByEquipamentoIdOrderByDescricao(equipamentoId);
	}
}
