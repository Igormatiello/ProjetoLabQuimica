package br.edu.utfpr.pb.labquimica.backend.service;


import br.edu.utfpr.pb.labquimica.backend.model.CreditoProfessor;
import br.edu.utfpr.pb.labquimica.backend.viewmodels.ResultadoOperacaoViewModel;

import java.security.Principal;

public interface CreditoProfessorService extends CrudService<CreditoProfessor, Long> {
    ResultadoOperacaoViewModel<CreditoProfessor> saveWithValidation(CreditoProfessor entity);
}
