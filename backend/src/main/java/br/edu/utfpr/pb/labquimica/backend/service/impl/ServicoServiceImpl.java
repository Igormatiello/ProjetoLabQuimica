package br.edu.utfpr.pb.labquimica.backend.service.impl;

import br.edu.utfpr.pb.labquimica.backend.model.Servico;
import br.edu.utfpr.pb.labquimica.backend.repository.ServicoRepository;
import br.edu.utfpr.pb.labquimica.backend.service.ServicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServicoServiceImpl extends CrudServiceImpl<Servico, Integer> implements ServicoService {

	@Autowired
	private ServicoRepository servicoRepository;
	
	@Override
	protected JpaRepository<Servico, Integer> getRepository() {
		return servicoRepository;
	}

	@Override
	public List<Servico> findAllByEquipamentoIdOrderByDescricao(Integer equipamentoId) {
		return servicoRepository.findAllByEquipamentoIdOrderByDescricao(equipamentoId);
	}
}
