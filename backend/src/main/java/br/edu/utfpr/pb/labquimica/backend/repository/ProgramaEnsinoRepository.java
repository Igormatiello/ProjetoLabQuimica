package br.edu.utfpr.pb.labquimica.backend.repository;

import br.edu.utfpr.pb.labquimica.backend.model.Instituicao;
import br.edu.utfpr.pb.labquimica.backend.model.ProgramaEnsino;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProgramaEnsinoRepository extends JpaRepository<ProgramaEnsino,Long> {
    ProgramaEnsino findByNomeOrSigla(String nome, String sigla);
}
