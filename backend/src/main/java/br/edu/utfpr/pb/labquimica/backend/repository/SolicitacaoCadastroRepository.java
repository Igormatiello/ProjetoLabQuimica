package br.edu.utfpr.pb.labquimica.backend.repository;

import br.edu.utfpr.pb.labquimica.backend.model.SolicitacaoCadastro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface SolicitacaoCadastroRepository extends JpaRepository<SolicitacaoCadastro, Long> {

    @Query("SELECT s FROM SolicitacaoCadastro s LEFT JOIN s.pessoa p where p is null")
    List<SolicitacaoCadastro> findSolicitacoesAbertas();

    List<SolicitacaoCadastro> findAllByDataCriacaoBetween(LocalDate dtIni, LocalDate dtFim);
}
