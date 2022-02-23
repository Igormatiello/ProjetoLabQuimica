package br.edu.utfpr.pb.labquimica.backend.testes.modelRepository;

import static org.assertj.core.api.Assertions.assertThat;

import java.text.Normalizer.Form;
import java.time.LocalDate;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import br.edu.utfpr.pb.labquimica.backend.enumerators.StatusFormulario;
import br.edu.utfpr.pb.labquimica.backend.enumerators.TipoPessoa;
import br.edu.utfpr.pb.labquimica.backend.enumerators.UnidadeMedidaCobrancaEquipamento;
import br.edu.utfpr.pb.labquimica.backend.model.Cidade;
import br.edu.utfpr.pb.labquimica.backend.model.CreditoProfessor;
import br.edu.utfpr.pb.labquimica.backend.model.Equipamento;
import br.edu.utfpr.pb.labquimica.backend.model.Formulario;
import br.edu.utfpr.pb.labquimica.backend.model.ParticipacaoProgramaEnsino;
import br.edu.utfpr.pb.labquimica.backend.model.Pessoa;
import br.edu.utfpr.pb.labquimica.backend.model.SolicitacaoCadastro;
import br.edu.utfpr.pb.labquimica.backend.repository.CidadeRepository;
import br.edu.utfpr.pb.labquimica.backend.repository.FormularioRepository;

@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@DataJpaTest
@AutoConfigureTestDatabase

public class FormularioRepositoryTests {

	@Autowired
	FormularioRepository repository;

	@Autowired
	TestEntityManager entityManager;

	@Test
	public void deveSalvarUmFormulario() {

		Formulario formulario = criarEPersistirFormulario();

		Formulario formularioSalvo = repository.save(formulario);

		Assertions.assertThat(formularioSalvo.getId()).isNotNull();

	}

	@Test
	public void deveDeletarUmFormulario() {

		Formulario formulario = criarEPersistirFormulario();

		formulario = entityManager.find(Formulario.class, formulario.getId());

		repository.delete(formulario);

		Formulario formularioDeletado = entityManager.find(Formulario.class, formulario.getId());

		Assertions.assertThat(formularioDeletado).isNull();
	}

	@Test
	public void deveBuscarUmFormualario() {
		Formulario formulario = criarEPersistirFormulario();

		java.util.Optional<Formulario> formularioEncontrado = repository.findById(formulario.getId());

		Assertions.assertThat(formularioEncontrado.isPresent()).isNotNull();

	}

	@Test
	public void deveBuscarUmFormualarioPorStatus() {
		Formulario formulario = criarEPersistirFormulario();

		java.util.List<Formulario> formularioAguardandoAnalise = repository
				.findByStatusOrderByDataSolicitacaoDesc(formulario.getStatus().AGUARDANDO_ANALISE);

		java.util.List<Formulario> formularioAguardandoAmostra = repository
				.findByStatusOrderByDataSolicitacaoDesc(formulario.getStatus().AGUARDANDO_AMOSTRA);

		java.util.List<Formulario> formularioAmostraRecusada = repository
				.findByStatusOrderByDataSolicitacaoDesc(formulario.getStatus().AMOSTRA_RECUSADA);

		java.util.List<Formulario> formularioEmAnalise = repository
				.findByStatusOrderByDataSolicitacaoDesc(formulario.getStatus().EM_ANALISE);

		java.util.List<Formulario> formularioFinalizado = repository
				.findByStatusOrderByDataSolicitacaoDesc(formulario.getStatus().FINALIZADO);

		Assertions.assertThat(formularioAguardandoAnalise).isNotNull();

		Assertions.assertThat(formularioAguardandoAmostra).isNull();
		Assertions.assertThat(formularioAmostraRecusada).isNull();
		Assertions.assertThat(formularioEmAnalise).isNull();
		Assertions.assertThat(formularioFinalizado).isNull();

	}

	@Test
	public void deveAtualizarUmFormulario() {

		Formulario formulario = criarEPersistirFormulario();
		;
		formulario.setStatus(StatusFormulario.EM_ANALISE);

		CreditoProfessor creditoProfessor = new CreditoProfessor();
		creditoProfessor.setValorCredito(11000d);
		formulario.setCreditoProfessor(creditoProfessor);

		ParticipacaoProgramaEnsino participacao = new ParticipacaoProgramaEnsino();
		participacao.setId(30l);

		formulario.setParticipacaoPrograma(participacao);

		repository.save(formulario);

		Formulario formularioNovo = entityManager.find(Formulario.class, formulario.getId());

		assertThat(formularioNovo.getStatus()).isEqualTo(StatusFormulario.EM_ANALISE);
		assertThat(formularioNovo.getParticipacaoPrograma().getId()).isEqualTo(30l);
		assertThat(formularioNovo.getCreditoProfessor().getValorCredito()).isEqualTo(11000d);

	}

	private Formulario criarEPersistirFormulario() {

		Formulario formulario = criarFormulario();
		entityManager.persist(formulario);
		return formulario;

	}

	public static Formulario criarFormulario() {

		Pessoa pessoa = PessoaRepositoryTests.criarPessoa();

		ParticipacaoProgramaEnsino participacaoProgramaEnsino = ParticipacaoProgramaEnsinoRepositoryTests
				.criarParticipacaoProgramaEnsino();

		Equipamento equipamento = EquipamentoRepositoryTests.criarEquipamento();

		CreditoProfessor creditoProfessor = new CreditoProfessor();
		creditoProfessor.setId(2l);
		creditoProfessor.setValorSaldo(2200d);
		creditoProfessor.setNomeProjeto("nome projeto");

		return Formulario.builder().pessoa(pessoa).participacaoPrograma(participacaoProgramaEnsino)
				.equipamento(equipamento).servico(null)// set
				.professor(pessoa).creditoProfessor(creditoProfessor).dataSolicitacao(LocalDate.now())
				.nomeProgramaEnsino("nome do programa").naturezaProjeto("quimico").nomeServico("analise")
				.metodologia("descricao").laudo("laudo em espera").status(StatusFormulario.AGUARDANDO_ANALISE)
				.motivoRecusaAmostra("").emailEnviadoSolicitante(false).emailEnviadoFuntef(false).analise(false)
				.numeroAmostras(4).imagem("imagem").anexos(null).build();

	}

}
