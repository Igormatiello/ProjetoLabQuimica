package br.edu.utfpr.pb.labquimica.backend.service;

import br.edu.utfpr.pb.labquimica.backend.model.Servico;

import java.util.List;

public interface ServicoService extends CrudService<Servico, Integer> {
    List<Servico> findAllByEquipamentoIdOrderByDescricao(Integer equipamentoId);
}
