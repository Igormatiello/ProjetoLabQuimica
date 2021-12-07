package br.edu.utfpr.pb.labquimica.backend.repository;

import br.edu.utfpr.pb.labquimica.backend.model.CreditoProfessor;
import br.edu.utfpr.pb.labquimica.backend.model.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CreditoProfessorRepository extends JpaRepository<CreditoProfessor, Long> {
    @Query(value = "SELECT * FROM credito_professor WHERE pessoa_id = ?1",nativeQuery = true)
    List<CreditoProfessor> buscaCreditosPorProfessor(Long pessoaId);
}
