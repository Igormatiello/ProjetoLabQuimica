package br.edu.utfpr.pb.labquimica.backend.testes.service;

import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;

import br.edu.utfpr.pb.labquimica.backend.repository.CidadeRepository;
import br.edu.utfpr.pb.labquimica.backend.repository.PapelRepository;
import br.edu.utfpr.pb.labquimica.backend.service.impl.CidadeServiceImpl;
import br.edu.utfpr.pb.labquimica.backend.service.impl.PapelServiceImpl;

public class PapelServiceTests {

	@SpyBean
	PapelServiceImpl service;
	
	@MockBean
	PapelRepository repository;
	
	
	
	
}
