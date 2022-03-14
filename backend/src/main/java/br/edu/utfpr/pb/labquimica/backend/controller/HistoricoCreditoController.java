package br.edu.utfpr.pb.labquimica.backend.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.utfpr.pb.labquimica.backend.model.HistoricoCredito;
import br.edu.utfpr.pb.labquimica.backend.model.Papel;
import br.edu.utfpr.pb.labquimica.backend.security.acessor.UserAccessor;
import br.edu.utfpr.pb.labquimica.backend.service.CrudService;
import br.edu.utfpr.pb.labquimica.backend.service.HistoricoCreditoService;
import lombok.NoArgsConstructor;

@RestController
@RequestMapping("historicoCredito")
@NoArgsConstructor
public class HistoricoCreditoController extends CrudController<HistoricoCredito, Long> {

	private HistoricoCreditoService historicoCreditoService;
	private UserAccessor usuarioAccessor;

	public HistoricoCreditoController(HistoricoCreditoService historicoCreditoService, UserAccessor usuarioAcessor) {

		this.usuarioAccessor = usuarioAcessor;
		this.historicoCreditoService = historicoCreditoService;
	}

	@Override
	protected CrudService<HistoricoCredito, Long> getService() {
		return historicoCreditoService;
	}

	@GetMapping("busca-por-professor")
	public List<HistoricoCredito> findFormulariosPessoa() {
		Papel papel = new Papel();
		papel.setId(1L);
		if (usuarioAccessor.getUsuario().getPapeis().contains(papel)) {
			return historicoCreditoService.findAll();
		}
		return historicoCreditoService.findByCreditoprofessorPessoaId(usuarioAccessor.getUsuario().getPessoa().getId());
	}
}
