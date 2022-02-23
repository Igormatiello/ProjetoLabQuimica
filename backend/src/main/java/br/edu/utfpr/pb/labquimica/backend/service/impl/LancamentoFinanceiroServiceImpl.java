package br.edu.utfpr.pb.labquimica.backend.service.impl;

import br.edu.utfpr.pb.labquimica.backend.model.LancamentoFinanceiro;
import br.edu.utfpr.pb.labquimica.backend.repository.InstituicaoRepository;
import br.edu.utfpr.pb.labquimica.backend.repository.LancamentoFinanceiroRepository;
import br.edu.utfpr.pb.labquimica.backend.service.LancamentoFinanceiroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class LancamentoFinanceiroServiceImpl extends CrudServiceImpl<LancamentoFinanceiro, Long>
		implements LancamentoFinanceiroService {

	private LancamentoFinanceiroRepository lancamentoFinanceiroRepository;

	public LancamentoFinanceiroServiceImpl(LancamentoFinanceiroRepository lancamentoFinanceiroRepository) {
		this.lancamentoFinanceiroRepository = lancamentoFinanceiroRepository;
	}

	@Override
	protected JpaRepository<LancamentoFinanceiro, Long> getRepository() {
		return lancamentoFinanceiroRepository;
	}
}