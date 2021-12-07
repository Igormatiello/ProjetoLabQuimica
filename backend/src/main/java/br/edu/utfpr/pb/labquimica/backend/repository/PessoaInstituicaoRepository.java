package br.edu.utfpr.pb.labquimica.backend.repository;

import br.edu.utfpr.pb.labquimica.backend.model.PessoaInstituicao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PessoaInstituicaoRepository extends JpaRepository<PessoaInstituicao, Long> {
    PessoaInstituicao findByInstituicaoNomeAndPessoaDocumentoAndEhProfessorTrue(String nome, String cpfProfessor);

    @Query("SELECT pi FROM PessoaInstituicao pi JOIN pi.pessoa p where p.usuario.id = :usuarioId")
    List<PessoaInstituicao> findPessoaInstituicao(Long usuarioId);

    @Query("SELECT pi FROM PessoaInstituicao pi JOIN pi.pessoa p where p.usuario.id = :usuarioId AND pi.instituicao.id = :instituicaoId")
    List<PessoaInstituicao> findPessoaInstituicao(Long usuarioId, Integer instituicaoId);

    List<PessoaInstituicao> findByInstituicaoId(Integer instituicaoId);

    List<PessoaInstituicao> findByPessoaId(Long pessoaId);

}
