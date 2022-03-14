package br.edu.utfpr.pb.labquimica.backend.service.impl;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import br.edu.utfpr.pb.labquimica.backend.model.Cidade;
import br.edu.utfpr.pb.labquimica.backend.repository.CidadeRepository;
import br.edu.utfpr.pb.labquimica.backend.service.CidadeService;

@Service
public class CidadeServiceImpl extends CrudServiceImpl<Cidade, Integer> implements CidadeService {

	private CidadeRepository cidadeRepository;

	public CidadeServiceImpl(CidadeRepository cidadeRepository) {
		this.cidadeRepository = cidadeRepository;
	}

	@Override
	protected JpaRepository<Cidade, Integer> getRepository() {
		return cidadeRepository;
	}

	@Override
	public List<Cidade> findByNomeContainingIgnoreCaseOrderByNomeAsc(String nome) {
		return cidadeRepository.findByNomeContainingIgnoreCaseOrderByNomeAsc(nome);
	}
}
