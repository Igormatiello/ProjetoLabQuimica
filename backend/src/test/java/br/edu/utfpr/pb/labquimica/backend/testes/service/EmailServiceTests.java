package br.edu.utfpr.pb.labquimica.backend.testes.service;

import org.junit.runner.RunWith;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import br.edu.utfpr.pb.labquimica.backend.service.impl.EmailServiceImpl;

@RunWith(SpringRunner.class)
@ActiveProfiles("test")


public class EmailServiceTests {

	@SpyBean
	EmailServiceImpl service;
	
	
	
	
}
