package br.edu.utfpr.pb.labquimica.backend.service.impl;

import br.edu.utfpr.pb.labquimica.backend.model.Instituicao;
import br.edu.utfpr.pb.labquimica.backend.model.ParticipacaoProgramaEnsino;
import br.edu.utfpr.pb.labquimica.backend.repository.InstituicaoRepository;
import br.edu.utfpr.pb.labquimica.backend.repository.ParticipacaoProgramaEnsinoRepository;
import br.edu.utfpr.pb.labquimica.backend.service.InstituicaoService;
import br.edu.utfpr.pb.labquimica.backend.service.ParticipacaoProgramaEnsinoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

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
