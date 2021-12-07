package br.edu.utfpr.pb.labquimica.backend.repository;

import br.edu.utfpr.pb.labquimica.backend.model.HistoricoCredito;
import br.edu.utfpr.pb.labquimica.backend.model.PessoaInstituicao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HistoricoCreditoRepository extends JpaRepository<HistoricoCredito, Long> {

    List<HistoricoCredito> findByCreditoProfessorPessoaId(Long pessoaId);
}
