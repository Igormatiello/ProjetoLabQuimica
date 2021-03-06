package br.edu.utfpr.pb.labquimica.backend.service.impl;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import br.edu.utfpr.pb.labquimica.backend.model.PessoaInstituicao;
import br.edu.utfpr.pb.labquimica.backend.repository.PessoaInstituicaoRepository;
import br.edu.utfpr.pb.labquimica.backend.service.PessoaInstituicaoService;

@Service
public class PessoaInstituicaoServiceImpl extends CrudServiceImpl<PessoaInstituicao, Long>
		implements PessoaInstituicaoService {

	private PessoaInstituicaoRepository pessoaInstituicaoRepository;

	public PessoaInstituicaoServiceImpl(PessoaInstituicaoRepository pessoaInstituicaoRepository) {
		this.pessoaInstituicaoRepository = pessoaInstituicaoRepository;
	}

	@Override
	protected JpaRepository<PessoaInstituicao, Long> getRepository() {
		return pessoaInstituicaoRepository;
	}

	@Override
	public PessoaInstituicao findByInstituicaoNomeAndPessoaDocumentoAndEhProfessorTrue(String nome,
			String cpfProfessor) {
		return pessoaInstituicaoRepository.findByInstituicaoNomeAndPessoaDocumentoAndEhProfessorTrue(nome,
				cpfProfessor);
	}

	@Override
	public List<PessoaInstituicao> findPessoaInstituicao(Long usuarioId) {
		return pessoaInstituicaoRepository.findPessoaInstituicao(usuarioId);
	}

	@Override
	public List<PessoaInstituicao> findPessoaInstituicao(Long usuarioId, Integer instituicaoId) {
		return pessoaInstituicaoRepository.findPessoaInstituicao(usuarioId, instituicaoId);
	}

	@Override
	public List<PessoaInstituicao> findByInstituicao(Integer instituicaoId) {
		return pessoaInstituicaoRepository.findByInstituicaoId(instituicaoId);
	}

	@Override
	public List<PessoaInstituicao> findByPessoaId(Long pessoaId) {
		return pessoaInstituicaoRepository.findByPessoaId(pessoaId);
	}
}
