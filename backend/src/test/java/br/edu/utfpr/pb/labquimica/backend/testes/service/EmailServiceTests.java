package br.edu.utfpr.pb.labquimica.backend.testes.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import br.edu.utfpr.pb.labquimica.backend.model.ConfEmail;
import br.edu.utfpr.pb.labquimica.backend.repository.ConfEmailRepository;
import br.edu.utfpr.pb.labquimica.backend.service.impl.EmailServiceImpl;

@RunWith(SpringRunner.class)
@ActiveProfiles("test")


public class EmailServiceTests {

	@SpyBean
	EmailServiceImpl service;
	
	@MockBean
	ConfEmailRepository repository;
	
	@Test
	public void mandaPropriedadesdoEmail() {
		
		ConfEmail email = new ConfEmail();
		
		email.setEmailRecebimento("");
		email.setHost("host");
		email.setId(1);
		email.setPorta(1);
		email.setRemetente("remetente");
		email.setSenha("1234");
		
		
	//	email.setUsuario("usuario 1");.
		
		
		repository.save(email);
		
		
		JavaMailSenderImpl java;
		
	}
	
	
	
}
