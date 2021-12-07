package br.edu.utfpr.pb.labquimica.backend.repository;

import br.edu.utfpr.pb.labquimica.backend.model.CreditoProfessor;
import br.edu.utfpr.pb.labquimica.backend.model.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface PessoaRepository extends JpaRepository<Pessoa, Long> {

    boolean existsByDocumento(String cpfCnpj);

    boolean existsBySolicitacaoCadastroId(Long id);

    @Query("select p.usuario.id from Pessoa p where p.id = :pessoaId")
    Long findUsuarioIdById(Long pessoaId);

    Pessoa findByUsuarioId(Long usuarioId);

    @Query(value = "SELECT Pessoa.* FROM Pessoa " +
            "JOIN solicitacao_cadastro ON solicitacao_cadastro.id = pessoa.solicitacaocadastro_id " +
            "WHERE solicitacao_cadastro.eh_professor = 'S'",nativeQuery = true)
    List<Pessoa> buscaProfessores();

    @Query(value = "SELECT Pessoa.* FROM Pessoa WHERE id IN " +
            "(SELECT pessoa_id FROM pessoa_instituicao as PI " +
            "JOIN participacao_programa_ensino as PP ON PP.orientador_id = PI.id or PP.participante_id = PI.id " +
            "WHERE PP.orientador_id = ?1 or PP.participante_id = ?1 and PI.eh_professor = 'S')",nativeQuery = true)
    List<Pessoa> buscaProfessoresFormularios(Long pessoaInstituicaoId);

    @Query(value = "SELECT * FROM Pessoa WHERE usuario_id = ?1",nativeQuery = true)
    List<Pessoa> buscaProfessorByUsuarioId(Long usuarioId);
}
