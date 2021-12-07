package br.edu.utfpr.pb.labquimica.backend.service;

import br.edu.utfpr.pb.labquimica.backend.model.PessoaInstituicao;

import java.util.List;

public interface PessoaInstituicaoService extends CrudService<PessoaInstituicao, Long> {
    PessoaInstituicao findByInstituicaoNomeAndPessoaDocumentoAndEhProfessorTrue(String nome, String cpfProfessor);
    List<PessoaInstituicao> findPessoaInstituicao(Long usuarioId);
    List<PessoaInstituicao> findPessoaInstituicao(Long usuarioId, Integer instituicaoId);
    List<PessoaInstituicao> findByInstituicao(Integer instituicaoId);
    List<PessoaInstituicao> findByPessoaId(Long pessoaId);
}
