package br.edu.utfpr.pb.labquimica.backend.controller;

import br.edu.utfpr.pb.labquimica.backend.model.LancamentoFinanceiro;
import br.edu.utfpr.pb.labquimica.backend.repository.LancamentoFinanceiroRepository;
import br.edu.utfpr.pb.labquimica.backend.service.CrudService;
import br.edu.utfpr.pb.labquimica.backend.service.LancamentoFinanceiroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("lancamento-financeiro")
public class LancamentoFinanceiroController extends CrudController<LancamentoFinanceiro, Long> {

    @Autowired
    private LancamentoFinanceiroService lancamentoFinanceiroService;

    @Autowired
    private LancamentoFinanceiroRepository lancamentoFinanceiroRepository;

    @Override
    protected CrudService<LancamentoFinanceiro, Long> getService() {
        return lancamentoFinanceiroService;
    }

    /*@GetMapping("buscaHistoricoFinanceiro/{id}")
    public List<HistoricoFinanceiro> buscaHistoricoFinanceiro(@PathVariable Long id){
        return LancamentoFinanceiroRepository.buscaLancamentoFinanceiro(id);
    }*/
}
