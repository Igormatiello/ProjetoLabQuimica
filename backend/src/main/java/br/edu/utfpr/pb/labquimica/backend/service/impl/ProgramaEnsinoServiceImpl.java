package br.edu.utfpr.pb.labquimica.backend.service.impl;

import br.edu.utfpr.pb.labquimica.backend.model.ProgramaEnsino;
import br.edu.utfpr.pb.labquimica.backend.repository.PessoaRepository;
import br.edu.utfpr.pb.labquimica.backend.repository.ProgramaEnsinoRepository;
import br.edu.utfpr.pb.labquimica.backend.service.ProgramaEnsinoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class ProgramaEnsinoServiceImpl extends CrudServiceImpl<ProgramaEnsino, Long> implements ProgramaEnsinoService {

    
    private ProgramaEnsinoRepository programaEnsinoRepository;
     
    
    public ProgramaEnsinoServiceImpl(ProgramaEnsinoRepository programaEnsinoRepository) {
	    this.programaEnsinoRepository = programaEnsinoRepository;
	    }

    @Override
    protected JpaRepository<ProgramaEnsino, Long> getRepository() {
        return programaEnsinoRepository;
    }
}
