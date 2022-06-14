package br.edu.utfpr.pb.labquimica.backend.service;

import br.edu.utfpr.pb.labquimica.backend.model.Cidade;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public interface CidadeService extends CrudService<Cidade, Integer> {
    List<Cidade> findByNomeContainingIgnoreCaseOrderByNomeAsc(String nome);
    List<Cidade> findByUf(String uf );
}
