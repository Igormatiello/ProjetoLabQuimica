package br.edu.utfpr.pb.labquimica.backend.service.impl;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import br.edu.utfpr.pb.labquimica.backend.model.HistoricoCredito;
import br.edu.utfpr.pb.labquimica.backend.repository.HistoricoCreditoRepository;
import br.edu.utfpr.pb.labquimica.backend.service.HistoricoCreditoService;

@Service
public class HistoricoCreditoServiceImpl extends CrudServiceImpl<HistoricoCredito, Long>
		implements HistoricoCreditoService {

	private HistoricoCreditoRepository historicoCreditoRepository;

	public HistoricoCreditoServiceImpl(HistoricoCreditoRepository historicoCreditoRepository) {
		this.historicoCreditoRepository = historicoCreditoRepository;
	}

	@Override
	public List<HistoricoCredito> findByCreditoprofessorPessoaId(Long pessoaId) {
		return historicoCreditoRepository.findByCreditoProfessorPessoaId(pessoaId);
	}

	@Override
	protected JpaRepository<HistoricoCredito, Long> getRepository() {
		return historicoCreditoRepository;
	}
}