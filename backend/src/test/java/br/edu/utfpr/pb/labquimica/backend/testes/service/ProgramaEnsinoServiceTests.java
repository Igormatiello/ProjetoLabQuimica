package br.edu.utfpr.pb.labquimica.backend.testes.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import br.edu.utfpr.pb.labquimica.backend.repository.ProgramaEnsinoRepository;
import br.edu.utfpr.pb.labquimica.backend.service.impl.ProgramaEnsinoServiceImpl;

@RunWith(SpringRunner.class)
@ActiveProfiles("test")

public class ProgramaEnsinoServiceTests {

	@SpyBean
	ProgramaEnsinoServiceImpl service;
	
	@MockBean
	ProgramaEnsinoRepository repository;

	@Test
	public void deve() {
		
		
		
	}
	
	
	
	
}
