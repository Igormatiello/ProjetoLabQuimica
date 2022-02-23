package br.edu.utfpr.pb.labquimica.backend.service.impl;

import br.edu.utfpr.pb.labquimica.backend.model.Papel;
import br.edu.utfpr.pb.labquimica.backend.repository.InstituicaoRepository;
import br.edu.utfpr.pb.labquimica.backend.repository.PapelRepository;
import br.edu.utfpr.pb.labquimica.backend.service.PapelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class PapelServiceImpl extends CrudServiceImpl<Papel, Long> implements PapelService {

	private PapelRepository papelRepository;

	public PapelServiceImpl(PapelRepository papelRepository) {
		this.papelRepository = papelRepository;
	}

	@Override
	protected JpaRepository<Papel, Long> getRepository() {
		return papelRepository;
	}
}
