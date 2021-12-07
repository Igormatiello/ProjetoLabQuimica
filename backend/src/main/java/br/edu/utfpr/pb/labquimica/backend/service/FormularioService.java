package br.edu.utfpr.pb.labquimica.backend.service;

import br.edu.utfpr.pb.labquimica.backend.enumerators.StatusFormulario;
import br.edu.utfpr.pb.labquimica.backend.model.Formulario;
import br.edu.utfpr.pb.labquimica.backend.model.IndicadorFormularioByDay;
import br.edu.utfpr.pb.labquimica.backend.viewmodels.FormularioAnaliseViewModel;
import br.edu.utfpr.pb.labquimica.backend.viewmodels.ResultadoOperacaoViewModel;

import java.time.LocalDate;
import java.util.List;

public interface FormularioService extends CrudService<Formulario, Long> {

    List<Formulario> findByStatusOrderByDataSolicitacaoDesc(StatusFormulario status);

    List<Formulario> findFormulariosByPessoaId(Long pessoaId);

    List<Formulario> buscaFormulariosPessoaJuridica(Long pessoaId);

    ResultadoOperacaoViewModel analisaFormulario(FormularioAnaliseViewModel formulario);

    List<Formulario> findAllByDataSolicitacaoBetween(LocalDate dtIni, LocalDate dtFim);

    List<IndicadorFormularioByDay> countAllByDataSolicitacaoBetween(LocalDate dtIni, LocalDate dtFim);
}
