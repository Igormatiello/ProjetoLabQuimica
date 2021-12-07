package br.edu.utfpr.pb.labquimica.backend.service;

import br.edu.utfpr.pb.labquimica.backend.model.SolicitacaoCadastro;
import br.edu.utfpr.pb.labquimica.backend.viewmodels.ResultadoOperacaoViewModel;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.time.LocalDate;
import java.util.List;

public interface SolicitacaoCadastroService extends CrudService<SolicitacaoCadastro, Long> {
    ResultadoOperacaoViewModel<SolicitacaoCadastro> saveWithValidation(SolicitacaoCadastro entity);
    List<SolicitacaoCadastro> findSolicitacoesAbertas();
    void sendEmailToResponsavelLiberacao();
    List<SolicitacaoCadastro> findAllByDataCriacaoBetween(LocalDate dtIni, LocalDate dtFim);
}
