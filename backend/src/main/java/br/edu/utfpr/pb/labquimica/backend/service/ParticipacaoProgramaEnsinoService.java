package br.edu.utfpr.pb.labquimica.backend.service;

import br.edu.utfpr.pb.labquimica.backend.model.ParticipacaoProgramaEnsino;

import java.util.List;

public interface ParticipacaoProgramaEnsinoService extends CrudService<ParticipacaoProgramaEnsino, Long> {
    ParticipacaoProgramaEnsino findByOrientadorIdAndProgramaEnsinoIdAndAndEhAtivoTrue(Long orientadorId, Long programaEnsinoId);

    List<ParticipacaoProgramaEnsino> findParticipacaoPrograma(Long pessoaInstituicaoId);
}
