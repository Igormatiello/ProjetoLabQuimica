package br.edu.utfpr.pb.labquimica.backend.testes.controller;

import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.Test;

import br.edu.utfpr.pb.labquimica.backend.controller.HistoricoCreditoController;
import br.edu.utfpr.pb.labquimica.backend.model.HistoricoCredito;
import br.edu.utfpr.pb.labquimica.backend.model.Papel;
import br.edu.utfpr.pb.labquimica.backend.security.acessor.UserAccessor;
import br.edu.utfpr.pb.labquimica.backend.service.HistoricoCreditoService;

public class HistoricoCreditoControllerTests {

	HistoricoCreditoService service;

	HistoricoCreditoController controller;

	@Test
	public void teste() {

		Papel papel;

		HistoricoCredito historicoCredito;
		// historicoCredito.getPessoa().getUsuario().setPapeis(Set<papel>);

		List<HistoricoCredito> resultado = controller.findFormulariosPessoa();

		// Assertions.assertThat(resultado).contains(historicoCredito);

		// System.out(resultado);

	}

}
