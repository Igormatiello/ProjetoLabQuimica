package br.edu.utfpr.pb.labquimica.backend.service.impl;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import br.edu.utfpr.pb.labquimica.backend.model.ParticipacaoProgramaEnsino;
import br.edu.utfpr.pb.labquimica.backend.repository.ParticipacaoProgramaEnsinoRepository;
import br.edu.utfpr.pb.labquimica.backend.service.ParticipacaoProgramaEnsinoService;

@Service
public class ParticipacaoProgramaEnsinoServiceImpl extends CrudServiceImpl<ParticipacaoProgramaEnsino, Long>
		implements ParticipacaoProgramaEnsinoService {

	private ParticipacaoProgramaEnsinoRepository participacaoProgramaEnsinoRepository;

	public ParticipacaoProgramaEnsinoServiceImpl(
			ParticipacaoProgramaEnsinoRepository participacaoProgramaEnsinoRepository) {
		this.participacaoProgramaEnsinoRepository = participacaoProgramaEnsinoRepository;
	}

	@Override
	protected JpaRepository<ParticipacaoProgramaEnsino, Long> getRepository() {
		return participacaoProgramaEnsinoRepository;
	}

	@Override
	public ParticipacaoProgramaEnsino findByOrientadorIdAndProgramaEnsinoIdAndAndEhAtivoTrue(Long orientadorId,
			Long programaEnsinoId) {
		return participacaoProgramaEnsinoRepository.findByOrientadorIdAndProgramaEnsinoIdAndAndEhAtivoTrue(orientadorId,
				programaEnsinoId);
	}

	@Override
	public List<ParticipacaoProgramaEnsino> findParticipacaoPrograma(Long pessoaInstituicaoId) {
		return participacaoProgramaEnsinoRepository.findParticipacaoPrograma(pessoaInstituicaoId);
	}
}
