package br.edu.utfpr.pb.labquimica.backend.service.impl;

import br.edu.utfpr.pb.labquimica.backend.model.CreditoProfessor;
import br.edu.utfpr.pb.labquimica.backend.repository.CreditoProfessorRepository;
import br.edu.utfpr.pb.labquimica.backend.service.CreditoProfessorService;
import br.edu.utfpr.pb.labquimica.backend.viewmodels.ResultadoOperacaoViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.time.LocalDate;

@Service
public class CreditoProfessorServiceImpl extends CrudServiceImpl<CreditoProfessor,Long> implements CreditoProfessorService {

    @Autowired
    private CreditoProfessorRepository creditoProfessorRepository;

    @Autowired
    private CreditoProfessorService creditoProfessorService;

    @Override
    public ResultadoOperacaoViewModel<CreditoProfessor> saveWithValidation(CreditoProfessor entity) {
        var result = new ResultadoOperacaoViewModel<CreditoProfessor>();

        boolean idExist;
        if (entity.getId() != null){
            idExist = creditoProfessorRepository.existsById(entity.getId());
        } else{
            idExist = false;
        }

        LocalDate dataAtual = LocalDate.now();

        if(idExist){
            entity.setDataInclusao(creditoProfessorService.findOne(entity.getId()).getDataInclusao());
        } else{
            entity.setDataInclusao(dataAtual);
        }

        entity.setDataAtualizacao(dataAtual);

        super.save(entity);

        return result.returningSuccess(entity);
    }

    @Override
    protected JpaRepository<CreditoProfessor, Long> getRepository() {
        return creditoProfessorRepository;
    }
}
