package br.edu.utfpr.pb.labquimica.backend.testes.service;

import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;

import br.edu.utfpr.pb.labquimica.backend.repository.AnexoFormularioRepository;
import br.edu.utfpr.pb.labquimica.backend.repository.CidadeRepository;
import br.edu.utfpr.pb.labquimica.backend.service.impl.AnexoFormularioServiceImpl;
import br.edu.utfpr.pb.labquimica.backend.service.impl.CidadeServiceImpl;

public class AnexoFormularioSeviceTests {

	
	@SpyBean
	AnexoFormularioServiceImpl service;
	
	@MockBean
	AnexoFormularioRepository repository;
	
}
