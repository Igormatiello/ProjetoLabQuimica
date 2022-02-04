package br.edu.utfpr.pb.labquimica.backend.service.impl;

import br.edu.utfpr.pb.labquimica.backend.model.Instituicao;
import br.edu.utfpr.pb.labquimica.backend.repository.InstituicaoRepository;
import br.edu.utfpr.pb.labquimica.backend.service.InstituicaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class InstituicaoServiceImpl extends CrudServiceImpl<Instituicao, Integer> implements InstituicaoService {

    
    private InstituicaoRepository instituicaoRepository;

    
    public InstituicaoServiceImpl(InstituicaoRepository instituicaoRepository) {
    this.instituicaoRepository = instituicaoRepository;
    }
    
    
    @Override
    protected JpaRepository<Instituicao, Integer> getRepository() {
        return instituicaoRepository;
        
    }
}
