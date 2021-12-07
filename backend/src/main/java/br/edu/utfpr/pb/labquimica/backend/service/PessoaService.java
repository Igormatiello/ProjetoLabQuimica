package br.edu.utfpr.pb.labquimica.backend.service;

import br.edu.utfpr.pb.labquimica.backend.enumerators.TipoPessoa;
import br.edu.utfpr.pb.labquimica.backend.model.Pessoa;
import br.edu.utfpr.pb.labquimica.backend.viewmodels.ResultadoOperacaoViewModel;
import br.edu.utfpr.pb.labquimica.backend.viewmodels.ResultadoOperacaoViewModel;
import br.edu.utfpr.pb.labquimica.backend.viewmodels.SolicitacaoCadastroViewModel;

public interface PessoaService extends CrudService<Pessoa, Long> {
    ResultadoOperacaoViewModel<Pessoa> saveValidarCadastro(SolicitacaoCadastroViewModel solicitacao);

    SolicitacaoCadastroViewModel getDadosValidacaoCadastro(long idSolicitacaoCadastro);

    ResultadoOperacaoViewModel<Object> findCpfCnpjCadastrado(TipoPessoa tipoPessoa, String cpfCnpj);

    void sendEmailRetornoSolicitacao(String emailTo);
}
