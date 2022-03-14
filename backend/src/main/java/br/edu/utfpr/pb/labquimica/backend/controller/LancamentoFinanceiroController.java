package br.edu.utfpr.pb.labquimica.backend.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.utfpr.pb.labquimica.backend.model.LancamentoFinanceiro;
import br.edu.utfpr.pb.labquimica.backend.repository.LancamentoFinanceiroRepository;
import br.edu.utfpr.pb.labquimica.backend.service.CrudService;
import br.edu.utfpr.pb.labquimica.backend.service.LancamentoFinanceiroService;
import lombok.NoArgsConstructor;

@RestController
@RequestMapping("lancamento-financeiro")
@NoArgsConstructor
public class LancamentoFinanceiroController extends CrudController<LancamentoFinanceiro, Long> {

	private LancamentoFinanceiroService lancamentoFinanceiroService;
	private LancamentoFinanceiroRepository lancamentoFinanceiroRepository;

	public LancamentoFinanceiroController(LancamentoFinanceiroService lancamentoFinanceiroService,
			LancamentoFinanceiroRepository lancamentoFinanceiroRepository) {
		this.lancamentoFinanceiroService = lancamentoFinanceiroService;
		this.lancamentoFinanceiroRepository = lancamentoFinanceiroRepository;
	}

	@Override
	protected CrudService<LancamentoFinanceiro, Long> getService() {
		return lancamentoFinanceiroService;
	}

	/*
	 * @GetMapping("buscaHistoricoFinanceiro/{id}") public List<HistoricoFinanceiro>
	 * buscaHistoricoFinanceiro(@PathVariable Long id){ return
	 * LancamentoFinanceiroRepository.buscaLancamentoFinanceiro(id); }
	 */
}
