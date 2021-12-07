package br.edu.utfpr.pb.labquimica.backend.repository;

import br.edu.utfpr.pb.labquimica.backend.model.Instituicao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface InstituicaoRepository extends JpaRepository<Instituicao,Integer> {
    Instituicao findByNome(String nome);
}
