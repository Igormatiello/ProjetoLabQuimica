package br.edu.utfpr.pb.labquimica.backend.service.impl;

import br.edu.utfpr.pb.labquimica.backend.model.Equipamento;
import br.edu.utfpr.pb.labquimica.backend.repository.EquipamentoRepository;
import br.edu.utfpr.pb.labquimica.backend.repository.InstituicaoRepository;
import br.edu.utfpr.pb.labquimica.backend.service.EquipamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class EquipamentoServiceImpl extends CrudServiceImpl<Equipamento, Integer> implements EquipamentoService {

	private EquipamentoRepository equipamentoRepository;

	public EquipamentoServiceImpl(EquipamentoRepository equipamentoRepository) {
		this.equipamentoRepository = equipamentoRepository;
	}

	@Override
	protected JpaRepository<Equipamento, Integer> getRepository() {
		return equipamentoRepository;
	}
}
