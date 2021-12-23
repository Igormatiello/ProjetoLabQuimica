package br.edu.utfpr.pb.labquimica.backend.testes.modelRepository;

import java.util.HashSet;

import org.assertj.core.api.Assertions;
import org.hibernate.mapping.Set;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import br.edu.utfpr.pb.labquimica.backend.enumerators.UnidadeMedidaCobrancaEquipamento;
import br.edu.utfpr.pb.labquimica.backend.model.Cidade;
import br.edu.utfpr.pb.labquimica.backend.model.Equipamento;
import br.edu.utfpr.pb.labquimica.backend.model.Papel;
import br.edu.utfpr.pb.labquimica.backend.model.ProgramaEnsino;
import br.edu.utfpr.pb.labquimica.backend.model.Servico;
import br.edu.utfpr.pb.labquimica.backend.repository.CidadeRepository;
import br.edu.utfpr.pb.labquimica.backend.repository.EquipamentoRepository;

@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@DataJpaTest
@AutoConfigureTestDatabase


public class EquipamentoRepositoryTests {

	
	
	@Autowired
	EquipamentoRepository repository;
	
	@Autowired
	TestEntityManager entityManager;
	
	@Test
	public void deveSalvarUmEquipamento() {
		
		Equipamento equipamento= criarEPersistirEquipamento();
				
		
		equipamento= repository.save(equipamento);
		Assertions.assertThat(equipamento.getId()).isNotNull();
		
		
		
	}
	
	@Test
	public void deveDeletarUmEquipamento() {
		
		Equipamento equipamento= criarEPersistirEquipamento();
		
		equipamento= entityManager.find(Equipamento.class, equipamento.getId());
		
		repository.delete(equipamento);
		
		Equipamento equipamentoDeletado = entityManager.find(Equipamento.class,equipamento.getId() );
		
		
		Assertions.assertThat(equipamentoDeletado).isNull();	
	}
	
	@Test
	public void deveBuscarUmEquipamento() {
		Equipamento equipamento= criarEPersistirEquipamento();
		
		
		
		java.util.Optional<Equipamento> equipamentoEncontrado= repository.findById(equipamento.getId());
		
		
		Assertions.assertThat(equipamentoEncontrado.isPresent()).isNotNull();
		
		
	}
	
	
	
	@Test
	public void deveAtualizarUmEquipamento() {
		
		Equipamento equipamento= criarEPersistirEquipamento();
		
		equipamento.setNome("novo");
		equipamento.setSigla("N");
		equipamento.setMetodologia("nova");
		equipamento.setLaudoPadrao("nova");
		equipamento.setObservacoesFixas("of nova");
		equipamento.setUnidadeMedidaExterno(UnidadeMedidaCobrancaEquipamento.Hora);
		equipamento.setValorInterno(222e11);
		equipamento.setUnidadeMedidaExterno(UnidadeMedidaCobrancaEquipamento.Hora);
		equipamento.setValorExterno(111e11);
		equipamento.setUnidadeMedidaPadrao(UnidadeMedidaCobrancaEquipamento.Hora);
		equipamento.setValorPadrao(121e11);

		java.util.Set<Servico> servicos;
		
		servicos = new HashSet<Servico>();
		
		Servico servico1 = new Servico();
		servico1.setDescricao("descrição 3");
		
		equipamento.setServicos(servicos);
		repository.save(equipamento);
		
		
		Equipamento equipamento2 = entityManager.find(Equipamento.class, equipamento.getId());
		
		
		Assertions.assertThat(equipamento2.getNome()).isEqualTo("novo");
		Assertions.assertThat(equipamento2.getSigla()).isEqualTo("N");
		Assertions.assertThat(equipamento2.getMetodologia()).isEqualTo("nova");
		Assertions.assertThat(equipamento2.getLaudoPadrao()).isEqualTo("nova");
		Assertions.assertThat(equipamento2.getObservacoesFixas()).isEqualTo("of nova");
		Assertions.assertThat(equipamento2.getUnidadeMedidaExterno()).isEqualTo(UnidadeMedidaCobrancaEquipamento.Hora);
		Assertions.assertThat(equipamento2.getValorInterno()).isEqualTo(222e11);
		Assertions.assertThat(equipamento2.getUnidadeMedidaExterno()).isEqualTo(UnidadeMedidaCobrancaEquipamento.Hora);
		Assertions.assertThat(equipamento2.getValorExterno()).isEqualTo(111e11);
		Assertions.assertThat(equipamento2.getUnidadeMedidaPadrao()).isEqualTo(UnidadeMedidaCobrancaEquipamento.Hora);
		Assertions.assertThat(equipamento2.getValorPadrao()).isEqualTo(121e11);
		Assertions.assertThat(equipamento2.getServicos().contains(servico1));
	}
	
	
	
	
	private Equipamento criarEPersistirEquipamento() {
		
		Equipamento equipamento= criarEquipamento();
		entityManager.persist(equipamento);
		return equipamento;
		
	}
	
	
	
	
	public static Equipamento criarEquipamento() {
		
		
		java.util.Set<Servico> servicos;
		
		servicos = new HashSet<Servico>();
		
		Servico servico1 = new Servico();
		servico1.setDescricao("descrição 1");
		
		Servico servico2 = new Servico();
		servico2.setDescricao("descrição 2");
				
		servicos.add(servico1);
		servicos.add(servico2);
		return
				Equipamento
				.builder()
				.nome("AA")
				.sigla("AAA")
				.metodologia("metodologia")
				.laudoPadrao("laudo padrão")
				.observacoesFixas("observações fixas")
				.unidadeMedidaExterno(UnidadeMedidaCobrancaEquipamento.Amostra)
				.valorInterno(222e10)
				.unidadeMedidaExterno(UnidadeMedidaCobrancaEquipamento.Amostra)
				.valorExterno(111e10)
				.unidadeMedidaPadrao(UnidadeMedidaCobrancaEquipamento.Amostra)
				.valorPadrao(121e10)
				.servicos(servicos)
				.build();
		
	}
	
	
}
