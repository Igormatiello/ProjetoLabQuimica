package br.edu.utfpr.pb.labquimica.backend.service.impl;

import br.edu.utfpr.pb.labquimica.backend.enumerators.StatusFormulario;
import br.edu.utfpr.pb.labquimica.backend.model.Formulario;
import br.edu.utfpr.pb.labquimica.backend.model.IndicadorFormularioByDay;
import br.edu.utfpr.pb.labquimica.backend.repository.FormularioRepository;
import br.edu.utfpr.pb.labquimica.backend.repository.InstituicaoRepository;
import br.edu.utfpr.pb.labquimica.backend.service.FormularioService;
import br.edu.utfpr.pb.labquimica.backend.utils.ValidationMessages;
import br.edu.utfpr.pb.labquimica.backend.viewmodels.FormularioAnaliseViewModel;
import br.edu.utfpr.pb.labquimica.backend.viewmodels.ResultadoOperacaoViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class FormularioServiceImpl extends CrudServiceImpl<Formulario, Long> implements FormularioService {

	private FormularioRepository formularioRepository;

	public FormularioServiceImpl(FormularioRepository formularioRepository) {
		this.formularioRepository = formularioRepository;
	}

	@Override
	protected JpaRepository<Formulario, Long> getRepository() {
		return formularioRepository;
	}

	@Override
	public List<Formulario> findByStatusOrderByDataSolicitacaoDesc(StatusFormulario status) {
		return formularioRepository.findByStatusOrderByDataSolicitacaoDesc(status);
	}

	@Override
	public List<Formulario> findFormulariosByPessoaId(Long pessoaId) {
		return formularioRepository.findByPessoaId(pessoaId);
	}

	@Override
	public List<Formulario> buscaFormulariosPessoaJuridica(Long pessoaId) {
		return formularioRepository.buscaFormulariosPessoaJuridica(pessoaId);
	}

	@Override
	public ResultadoOperacaoViewModel analisaFormulario(FormularioAnaliseViewModel formulario) {
		var result = new ResultadoOperacaoViewModel();
		var form = findOne(formulario.getFormularioId());

		if (formulario.getStatus().equals(StatusFormulario.AMOSTRA_RECUSADA)
				&& (formulario.getMotivoRejeicao() == null || formulario.getMotivoRejeicao().isBlank())) {
			result.addFailMessage(ValidationMessages.MotivoRejeicao);
			result.setSucesso(false);
			return result;
		} else if (formulario.getStatus().equals(StatusFormulario.AMOSTRA_RECUSADA)) {
			form.setStatus(formulario.getStatus());
			form.setMotivoRecusaAmostra(formulario.getMotivoRejeicao());
		} else if (formulario.getStatus().equals(StatusFormulario.AGUARDANDO_AMOSTRA)
				|| formulario.getStatus().equals(StatusFormulario.AGUARDANDO_ANALISE)
				|| formulario.getStatus().equals(StatusFormulario.EM_ANALISE)
				|| formulario.getStatus().equals(StatusFormulario.FINALIZADO)) {
			form.setStatus(formulario.getStatus());
			result.setSucesso(true);
		} else {
			result.addFailMessage(ValidationMessages.StatusValido);
			result.setSucesso(false);
			return result;
		}
		this.save(form);
		return result;
	}

	@Override
	public List<Formulario> findAllByDataSolicitacaoBetween(LocalDate dtIni, LocalDate dtFim) {
		return formularioRepository.findAllByDataSolicitacaoBetween(dtIni, dtFim);
	}

	@Override
	public List<IndicadorFormularioByDay> countAllByDataSolicitacaoBetween(LocalDate dtIni, LocalDate dtFim) {
		return formularioRepository.countAllByDataSolicitacaoBetween(dtIni, dtFim);
	}
}
