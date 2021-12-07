package br.edu.utfpr.pb.labquimica.backend.service;

import br.edu.utfpr.pb.labquimica.backend.model.ConfEmail;

public interface ConfEmailService extends CrudService<ConfEmail, Integer> {

    ConfEmail findConfig();

    void sendEmailTeste();
}
