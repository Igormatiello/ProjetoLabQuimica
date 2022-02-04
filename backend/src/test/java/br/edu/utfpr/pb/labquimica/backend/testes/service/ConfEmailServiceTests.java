package br.edu.utfpr.pb.labquimica.backend.testes.service;

import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import br.edu.utfpr.pb.labquimica.backend.model.ConfEmail;
import br.edu.utfpr.pb.labquimica.backend.model.Servico;
import br.edu.utfpr.pb.labquimica.backend.repository.ConfEmailRepository;
import br.edu.utfpr.pb.labquimica.backend.service.impl.ConfEmailServiceImpl;


@RunWith(SpringRunner.class)
@ActiveProfiles("test")


public class ConfEmailServiceTests {
	
	@SpyBean
	ConfEmailServiceImpl service;
	
	@MockBean
	ConfEmailRepository repository;
	
	
	@Test
	public void retornaEmailsConfigurados() {
		
		ConfEmail conf1= new ConfEmail().builder().id(1)
				.host("host")
				.porta(1)
				.remetente("remetente")
				.usuario("usuario 1")
				.senha("senha 1")
				.emailRecebimento("email recebimento")
				.build();
		
		
		ConfEmail conf2= new ConfEmail().builder().id(1)
				.host("host")
				.porta(2)
				.remetente("remetente")
				.usuario("usuario 2")
				.senha("senha 2")
				.emailRecebimento("email recebimento")
				.build();
		
		
		repository.save(conf1);
		repository.save(conf2);
	
		service.save(conf1);
		service.save(conf2);
		
		Mockito.when(service.findConfig()).thenReturn(conf1,conf2);
		
	
   		
		ConfEmail resultado = service.findConfig();
	Assertions.assertThat(resultado).isNotNull();
		
	}
	
}
