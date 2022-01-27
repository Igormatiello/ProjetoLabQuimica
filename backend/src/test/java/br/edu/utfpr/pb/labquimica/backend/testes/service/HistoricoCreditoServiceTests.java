package br.edu.utfpr.pb.labquimica.backend.testes.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import br.edu.utfpr.pb.labquimica.backend.model.CreditoProfessor;
import br.edu.utfpr.pb.labquimica.backend.model.HistoricoCredito;
import br.edu.utfpr.pb.labquimica.backend.model.Pessoa;
import br.edu.utfpr.pb.labquimica.backend.model.Servico;
import br.edu.utfpr.pb.labquimica.backend.repository.CreditoProfessorRepository;
import br.edu.utfpr.pb.labquimica.backend.repository.HistoricoCreditoRepository;
import br.edu.utfpr.pb.labquimica.backend.repository.PessoaRepository;
import br.edu.utfpr.pb.labquimica.backend.service.impl.HistoricoCreditoServiceImpl;
import br.edu.utfpr.pb.labquimica.backend.testes.modelRepository.PessoaRepositoryTests;

@RunWith(SpringRunner.class)
@ActiveProfiles("test")


public class HistoricoCreditoServiceTests {

	
	@SpyBean
	HistoricoCreditoServiceImpl service;
	
	@MockBean
	HistoricoCreditoRepository repository;
	
	PessoaRepositoryTests pessoaTest;
	
	PessoaRepository repPessoa;
	
	CreditoProfessorRepository repCredito;

	
	@Test
	public void deveBuscarCreditoPorIdDaPessoa() {
		
		Pessoa pessoa= pessoaTest.criarPessoa();
		
		pessoa.setId(1l);
		
		repPessoa.save(pessoa);
		
		CreditoProfessor creditoProfessor= new CreditoProfessor();
		
		creditoProfessor.setId(1l);
		creditoProfessor.setDataAtualizacao(LocalDate.now());
		creditoProfessor.setDataInclusao(LocalDate.MAX);
		creditoProfessor.setNomeProjeto("projeto do igor");
		creditoProfessor.setPessoa(pessoa);
		creditoProfessor.setValorCredito(100.00);
		creditoProfessor.setValorSaldo(200.00);
		
		repCredito.save(creditoProfessor);
		
		List<HistoricoCredito> listaCreditos=new ArrayList<>();
		
		HistoricoCredito cred1=new HistoricoCredito();
		cred1.setPessoa(pessoa);
		cred1.setValorPago(0.00);
		cred1.setId(1l);
		
		HistoricoCredito cred2=new HistoricoCredito();
		cred2.setPessoa(pessoa);
		cred2.setValorPago(100.00);
		cred2.setId(1l);
		
		
		listaCreditos.add(cred1);
		listaCreditos.add(cred2);
		
		repository.save(cred1);
		repository.save(cred2);
		
		List<HistoricoCredito> resultado= service.findByCreditoprofessorPessoaId(1l);
		
		Assertions.assertThat(resultado).isNotNull();
		Assertions.assertThat(resultado).contains(cred1,cred2);
		Assertions.assertThat(resultado).hasSize(2);
		
		
	}
	
}
