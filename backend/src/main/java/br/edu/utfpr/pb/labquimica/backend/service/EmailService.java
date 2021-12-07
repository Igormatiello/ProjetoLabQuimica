package br.edu.utfpr.pb.labquimica.backend.service;

import br.edu.utfpr.pb.labquimica.backend.utils.Email;

public interface EmailService {

    void enviar(Email email);

    void enviarSemThread(Email email);

    void enviarToEmailPadrao(Email email);

    void enviarToEmailPadraoSemThread(Email email);
}
