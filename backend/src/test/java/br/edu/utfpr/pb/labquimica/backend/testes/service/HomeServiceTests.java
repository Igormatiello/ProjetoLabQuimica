package br.edu.utfpr.pb.labquimica.backend.testes.service;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import br.edu.utfpr.pb.labquimica.backend.model.Indicadores;
import br.edu.utfpr.pb.labquimica.backend.model.SolicitacaoCadastro;
import br.edu.utfpr.pb.labquimica.backend.repository.SolicitacaoCadastroRepository;
import br.edu.utfpr.pb.labquimica.backend.service.impl.HomeServiceImpl;
import br.edu.utfpr.pb.labquimica.backend.service.impl.SolicitacaoCadastroServiceImpl;
import br.edu.utfpr.pb.labquimica.backend.testes.modelRepository.SolicitacaoCadastroRepositoryTests;



@RunWith(SpringRunner.class)
@ActiveProfiles("test")


public class HomeServiceTests {

	@SpyBean
	HomeServiceImpl service;
	
	
	SolicitacaoCadastroServiceImpl SCSImpl;
	SolicitacaoCadastroRepositoryTests rep;
	SolicitacaoCadastroRepository rep2;
	
	@Test 
	public void retornaIndicadoresPelaData() {
		
		SolicitacaoCadastro SC= rep.criarSolicitacaoCadastro();
		SC.setId(1l);
		
		rep2.save(SC);

		
		Indicadores indicadores = service.findDadosIndicadores(null, null);
		
		Assertions.assertThat(indicadores.getSolicitacaoCadastrosPendentes()).contains(SC);
		
		
		
	}
	
	
}
