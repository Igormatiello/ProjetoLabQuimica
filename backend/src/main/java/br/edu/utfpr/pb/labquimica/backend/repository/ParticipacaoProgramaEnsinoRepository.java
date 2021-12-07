package br.edu.utfpr.pb.labquimica.backend.repository;

import br.edu.utfpr.pb.labquimica.backend.model.ParticipacaoProgramaEnsino;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ParticipacaoProgramaEnsinoRepository extends JpaRepository<ParticipacaoProgramaEnsino, Long> {
    ParticipacaoProgramaEnsino findByOrientadorIdAndProgramaEnsinoIdAndAndEhAtivoTrue(Long orientadorId, Long programaEnsinoId);

    @Query("SELECT ppe FROM ParticipacaoProgramaEnsino ppe LEFT JOIN ppe.participante part LEFT JOIN ppe.orientador ori " +
            "WHERE part.id = :pessoaInstituicaoId" +
            " OR ori.id = :pessoaInstituicaoId")
    List<ParticipacaoProgramaEnsino> findParticipacaoPrograma(Long pessoaInstituicaoId);
}
