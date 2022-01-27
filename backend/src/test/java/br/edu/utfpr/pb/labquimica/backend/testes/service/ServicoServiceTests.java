package br.edu.utfpr.pb.labquimica.backend.testes.service;

import java.util.ArrayList;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import br.edu.utfpr.pb.labquimica.backend.model.Cidade;
import br.edu.utfpr.pb.labquimica.backend.model.Equipamento;
import br.edu.utfpr.pb.labquimica.backend.model.Servico;
import br.edu.utfpr.pb.labquimica.backend.repository.PessoaRepository;
import br.edu.utfpr.pb.labquimica.backend.repository.ServicoRepository;
import br.edu.utfpr.pb.labquimica.backend.service.impl.PessoaServiceImpl;
import br.edu.utfpr.pb.labquimica.backend.service.impl.ServicoServiceImpl;
import br.edu.utfpr.pb.labquimica.backend.testes.modelRepository.EquipamentoRepositoryTests;

@RunWith(SpringRunner.class)
@ActiveProfiles("test")

public class ServicoServiceTests {

	@SpyBean
	ServicoServiceImpl service;
	
	@MockBean
	ServicoRepository repository;
	
	EquipamentoRepositoryTests equipamentoTest;
	
	
	@Test
	public void deveObterUmaListaDeServicos() {
		
	Equipamento equipamento1= new Equipamento();
	equipamento1= equipamentoTest.criarEquipamento();
	equipamento1.setId(1);
	
	Equipamento equipamento2= new Equipamento();
	equipamento2= equipamentoTest.criarEquipamento();
	equipamento2.setId(2);
	
	Equipamento equipamento3= new Equipamento();
	equipamento3= equipamentoTest.criarEquipamento();
	equipamento3.setId(3);
	
	Equipamento equipamento4= new Equipamento();
	equipamento4= equipamentoTest.criarEquipamento();
	equipamento4.setId(4);
	
	
	List<Servico> listaServicos=new ArrayList<>();
	
	Servico serv1= new Servico();
	serv1.setEquipamento(equipamento1);
	serv1.setId(1);
	serv1.setDescricao("primeiro");
	
	Servico serv2= new Servico();
	serv2.setEquipamento(equipamento1);
	serv2.setId(2);
	serv2.setDescricao("segundo");;
	
	Servico serv3= new Servico();
	serv3.setEquipamento(equipamento3);
	serv3.setId(3);
	serv3.setDescricao("terceiro");
	
	Servico serv4= new Servico();
	serv4.setEquipamento(equipamento4);
	serv4.setId(4);
	serv4.setDescricao("quarto");
	
	
	
	//listaServicos.add(serv4);
	//listaServicos.add(serv3);
	listaServicos.add(serv2);
	listaServicos.add(serv1);
	

		

    Mockito.when(repository.findAllByEquipamentoIdOrderByDescricao(1)).thenReturn( listaServicos);
    		
    Mockito.when(repository.findAllByEquipamentoIdOrderByDescricao(2)).thenReturn( null);
	
			
			
			
			
			List<Servico> resultado= service.findAllByEquipamentoIdOrderByDescricao(1);
			
			Assertions.assertThat(resultado).isNotEmpty().hasSize(2).contains(serv2,serv1);
			
			List<Servico> resultado2= service.findAllByEquipamentoIdOrderByDescricao(3);
			
			Assertions.assertThat(resultado2).isEmpty();

			
			
	}
	
	
}
