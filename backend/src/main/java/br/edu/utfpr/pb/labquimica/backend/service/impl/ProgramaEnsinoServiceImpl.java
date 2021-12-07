package br.edu.utfpr.pb.labquimica.backend.service.impl;

import br.edu.utfpr.pb.labquimica.backend.model.ProgramaEnsino;
import br.edu.utfpr.pb.labquimica.backend.repository.ProgramaEnsinoRepository;
import br.edu.utfpr.pb.labquimica.backend.service.ProgramaEnsinoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class ProgramaEnsinoServiceImpl extends CrudServiceImpl<ProgramaEnsino, Long> implements ProgramaEnsinoService {

    @Autowired
    private ProgramaEnsinoRepository programaEnsinoRepository;

    @Override
    protected JpaRepository<ProgramaEnsino, Long> getRepository() {
        return programaEnsinoRepository;
    }
}
