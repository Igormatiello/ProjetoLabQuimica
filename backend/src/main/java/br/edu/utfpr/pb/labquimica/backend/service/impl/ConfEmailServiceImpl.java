package br.edu.utfpr.pb.labquimica.backend.service.impl;

import br.edu.utfpr.pb.labquimica.backend.model.ConfEmail;
import br.edu.utfpr.pb.labquimica.backend.repository.ConfEmailRepository;
import br.edu.utfpr.pb.labquimica.backend.repository.InstituicaoRepository;
import br.edu.utfpr.pb.labquimica.backend.service.ConfEmailService;
import br.edu.utfpr.pb.labquimica.backend.service.EmailService;
import br.edu.utfpr.pb.labquimica.backend.utils.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConfEmailServiceImpl extends CrudServiceImpl<ConfEmail, Integer> implements ConfEmailService {

    
    private ConfEmailRepository confEmailRepository;
    
    public ConfEmailServiceImpl(ConfEmailRepository confEmailRepository) {
        this.confEmailRepository = confEmailRepository;
        }
    

    private EmailService emailService;
    
    
    public ConfEmailServiceImpl (EmailService emailService) {
    	this.emailService=emailService;
    	
    }
    
    

    @Override
    protected JpaRepository<ConfEmail, Integer> getRepository() {
        return confEmailRepository;
    }

    @Override
    public ConfEmail findConfig() {
        List<ConfEmail> config = confEmailRepository.findAll();
        return config != null && config.size() > 0 ? config.get(0) : null;
    }

    @Override
    public void sendEmailTeste() {
        Email emailTeste = new Email()
                .setTitulo("Teste")
                .setConteudo("Teste de envio de email");
        emailService.enviarToEmailPadraoSemThread(emailTeste);
    }
}
