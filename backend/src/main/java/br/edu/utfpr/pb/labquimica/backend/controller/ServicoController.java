package br.edu.utfpr.pb.labquimica.backend.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.edu.utfpr.pb.labquimica.backend.model.Servico;
import br.edu.utfpr.pb.labquimica.backend.service.CrudService;
import br.edu.utfpr.pb.labquimica.backend.service.ServicoService;

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
