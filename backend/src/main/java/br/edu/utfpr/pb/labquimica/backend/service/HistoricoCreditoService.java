package br.edu.utfpr.pb.labquimica.backend.service;

import br.edu.utfpr.pb.labquimica.backend.model.HistoricoCredito;

import java.util.List;

public interface HistoricoCreditoService extends CrudService<HistoricoCredito, Long> {
    List<HistoricoCredito> findByCreditoprofessorPessoaId(Long pessoaId);
}
