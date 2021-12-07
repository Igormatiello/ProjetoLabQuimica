package br.edu.utfpr.pb.labquimica.backend.repository;

import br.edu.utfpr.pb.labquimica.backend.enumerators.StatusFormulario;
import br.edu.utfpr.pb.labquimica.backend.model.Formulario;
import br.edu.utfpr.pb.labquimica.backend.model.IndicadorFormularioByDay;
import br.edu.utfpr.pb.labquimica.backend.model.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface FormularioRepository extends JpaRepository<Formulario, Long> {
    List<Formulario> findByStatusOrderByDataSolicitacaoDesc(StatusFormulario status);

    @Query("SELECT f FROM Formulario f JOIN f.participacaoPrograma partPrograma LEFT JOIN partPrograma.participante part JOIN partPrograma.orientador orientador " +
            "where part.pessoa.id = :pessoaId OR orientador.pessoa.id = :pessoaId")
    List<Formulario> findByPessoaId(Long pessoaId);

    List<Formulario> findAllByDataSolicitacaoBetween(LocalDate dtIni, LocalDate dtFim);

    @Query("SELECT new br.edu.utfpr.pb.labquimica.backend.model.IndicadorFormularioByDay(count(f), f.dataSolicitacao) " +
            "FROM Formulario f " +
            "WHERE f.dataSolicitacao between :dtIni and :dtFim " +
            "GROUP BY f.dataSolicitacao")
    List<IndicadorFormularioByDay> countAllByDataSolicitacaoBetween(@Param("dtIni") LocalDate dtIni,
                                                                    @Param("dtFim") LocalDate dtFim);

    @Query(value = "SELECT * FROM Formulario WHERE pessoa_id = ?1",nativeQuery = true)
    List<Formulario> buscaFormulariosPessoaJuridica(Long pessoaId);
}
