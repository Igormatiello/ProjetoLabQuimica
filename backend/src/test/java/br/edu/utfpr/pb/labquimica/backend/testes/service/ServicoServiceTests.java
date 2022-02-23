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
import br.edu.utfpr.pb.labquimica.backend.testes.modelRepository.CriarObjetos;
import br.edu.utfpr.pb.labquimica.backend.testes.modelRepository.EquipamentoRepositoryTests;

@RunWith(SpringRunner.class)
@ActiveProfiles("test")

public class ServicoServiceTests {

	@SpyBean
	ServicoServiceImpl service;

	@MockBean
	ServicoRepository repository;

	@Test
	public void deveObterUmaListaDeServicos() {

		Equipamento equipamento1 = new Equipamento();
		equipamento1 = CriarObjetos.criarEquipamento();
		equipamento1.setId(1);

		Servico serv1 = new Servico();
		serv1.setEquipamento(equipamento1);
		serv1.setId(1);
		serv1.setDescricao("primeiro");

		Servico serv2 = new Servico();
		serv2.setEquipamento(equipamento1);
		serv2.setId(1);
		serv2.setDescricao("segundo");

		List<Servico> resultado = service.findAllByEquipamentoIdOrderByDescricao(1);

		Assertions.assertThat(resultado).isNotEmpty().hasSize(2).contains(serv2, serv1);

	}

	@Test
	public void deveRetornarUmaListaVaziaDeServicos() {

		Equipamento equipamento1 = new Equipamento();
		equipamento1 = CriarObjetos.criarEquipamento();
		equipamento1.setId(1);

		Servico serv1 = new Servico();
		serv1.setEquipamento(equipamento1);
		serv1.setId(1);
		serv1.setDescricao("descricao");

		Servico serv2 = new Servico();
		serv2.setEquipamento(equipamento1);
		serv2.setId(1);
		serv2.setDescricao("segundo");

		List<Servico> resultado = service.findAllByEquipamentoIdOrderByDescricao(2);

		Assertions.assertThat(resultado).isNull();

	}

}
