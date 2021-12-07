package br.edu.utfpr.pb.labquimica.backend.service.impl;

import br.edu.utfpr.pb.labquimica.backend.model.Cidade;
import br.edu.utfpr.pb.labquimica.backend.repository.CidadeRepository;
import br.edu.utfpr.pb.labquimica.backend.service.CidadeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CidadeServiceImpl extends CrudServiceImpl<Cidade,Integer> implements CidadeService {

    @Autowired
    private CidadeRepository cidadeRepository;
    @Override
    protected JpaRepository<Cidade, Integer> getRepository() {
        return cidadeRepository;
    }

    @Override
    public List<Cidade> findByNomeContainingIgnoreCaseOrderByNomeAsc(String nome) {
        return cidadeRepository.findByNomeContainingIgnoreCaseOrderByNomeAsc(nome);
    }
}
