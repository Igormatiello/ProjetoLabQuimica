package br.edu.utfpr.pb.labquimica.backend.service.impl;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import br.edu.utfpr.pb.labquimica.backend.model.Servico;
import br.edu.utfpr.pb.labquimica.backend.repository.ServicoRepository;
import br.edu.utfpr.pb.labquimica.backend.service.ServicoService;

@Service
public class ServicoServiceImpl extends CrudServiceImpl<Servico, Integer> implements ServicoService {

	private ServicoRepository servicoRepository;

	public ServicoServiceImpl(ServicoRepository servicoRepository) {
		this.servicoRepository = servicoRepository;
	}

	@Override
	protected JpaRepository<Servico, Integer> getRepository() {
		return servicoRepository;
	}

	@Override
	public List<Servico> findAllByEquipamentoIdOrderByDescricao(Integer equipamentoId) {
		return servicoRepository.findAllByEquipamentoIdOrderByDescricao(equipamentoId);
	}
}
