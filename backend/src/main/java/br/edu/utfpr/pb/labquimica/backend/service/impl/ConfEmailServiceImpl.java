package br.edu.utfpr.pb.labquimica.backend.service.impl;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import br.edu.utfpr.pb.labquimica.backend.model.ConfEmail;
import br.edu.utfpr.pb.labquimica.backend.repository.ConfEmailRepository;
import br.edu.utfpr.pb.labquimica.backend.service.ConfEmailService;
import br.edu.utfpr.pb.labquimica.backend.service.EmailService;
import br.edu.utfpr.pb.labquimica.backend.utils.Email;
import lombok.NoArgsConstructor;

@Service
@NoArgsConstructor
public class ConfEmailServiceImpl extends CrudServiceImpl<ConfEmail, Integer> implements ConfEmailService {

	private ConfEmailRepository confEmailRepository;

	private EmailService emailService;

	public ConfEmailServiceImpl(ConfEmailRepository confEmailRepository, EmailService emailService) {
		this.confEmailRepository = confEmailRepository;
		this.emailService = emailService;
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
		Email emailTeste = new Email().setTitulo("Teste").setConteudo("Teste de envio de email");
		emailService.enviarToEmailPadraoSemThread(emailTeste);
	}
}
