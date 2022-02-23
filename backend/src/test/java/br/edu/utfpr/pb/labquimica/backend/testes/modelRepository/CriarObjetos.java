package br.edu.utfpr.pb.labquimica.backend.testes.modelRepository;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

//import com.sun.tools.classfile.Opcode.Set;

import br.edu.utfpr.pb.labquimica.backend.enumerators.StatusFormulario;
import br.edu.utfpr.pb.labquimica.backend.enumerators.TipoInstituicao;
import br.edu.utfpr.pb.labquimica.backend.enumerators.TipoPessoa;
import br.edu.utfpr.pb.labquimica.backend.enumerators.UnidadeMedidaCobrancaEquipamento;
import br.edu.utfpr.pb.labquimica.backend.model.Cidade;
import br.edu.utfpr.pb.labquimica.backend.model.CreditoProfessor;
import br.edu.utfpr.pb.labquimica.backend.model.Equipamento;
import br.edu.utfpr.pb.labquimica.backend.model.Formulario;
import br.edu.utfpr.pb.labquimica.backend.model.Instituicao;
import br.edu.utfpr.pb.labquimica.backend.model.Papel;
import br.edu.utfpr.pb.labquimica.backend.model.ParticipacaoProgramaEnsino;
import br.edu.utfpr.pb.labquimica.backend.model.Pessoa;
import br.edu.utfpr.pb.labquimica.backend.model.PessoaInstituicao;
import br.edu.utfpr.pb.labquimica.backend.model.ProgramaEnsino;
import br.edu.utfpr.pb.labquimica.backend.model.Servico;
import br.edu.utfpr.pb.labquimica.backend.model.SolicitacaoCadastro;
import br.edu.utfpr.pb.labquimica.backend.model.Usuario;

public class CriarObjetos {

	public static Cidade criarCidade() {
		Cidade cidade = new Cidade();
		cidade.setId(1);
		cidade.setNome("xaxim");
		cidade.setUf("SC");

		return cidade;

	}

	public static Cidade criarCidade2() {
		Cidade cidade2 = new Cidade();
		cidade2.setNome("cidade padrão");
		cidade2.setUf("uf padrão");
		cidade2.setId(1);

		return cidade2;

	}

	public static Instituicao criarInstituicao() {

		Instituicao instituicao = new Instituicao();
		instituicao.setNome("Utfpr");
		instituicao.setCidade(criarCidade2());
		instituicao.setId(1);
		instituicao.setTipoInstituicao(TipoInstituicao.Interna);

		return instituicao;

	}

	public static List<PessoaInstituicao> criarListaVinculos() {

		List<PessoaInstituicao> listaVinculos = new ArrayList<>();
		PessoaInstituicao lista1 = new PessoaInstituicao();
		lista1.setId(1L);
		lista1.setEhProfessor(true);
		lista1.setInstituicao(criarInstituicao());
		lista1.setPessoa(criarPessoa());
		lista1.setCreditoVigente(200.00);
		lista1.setEhAtivo(false);

		listaVinculos.add(lista1);

		PessoaInstituicao lista2 = new PessoaInstituicao();
		lista2.setId(2L);
		lista2.setEhProfessor(false);
		lista1.setInstituicao(criarInstituicao());
		lista1.setPessoa(criarPessoa());
		lista1.setCreditoVigente(300.00);
		lista1.setEhAtivo(true);

		listaVinculos.add(lista2);

		return listaVinculos;
	}

	public static PessoaInstituicao criarPessoaInstituicao() {

		PessoaInstituicao pi = new PessoaInstituicao();
		pi.setId(1L);
		pi.setEhProfessor(true);
		pi.setInstituicao(criarInstituicao());
		pi.setPessoa(criarPessoa());
		pi.setCreditoVigente(200.00);
		pi.setEhAtivo(false);

		return pi;
	}

	public static java.util.Set<Papel> criarPapeis() {
		java.util.Set<Papel> papeis;
		papeis = new HashSet<Papel>();

		Papel papel1 = new Papel();
		papel1.setId(1l);
		papel1.setNome("papel n1");

		Papel papel2 = new Papel();
		papel2.setId(2l);
		papel2.setNome("papel n2");

		papeis.add(papel1);
		papeis.add(papel2);

		return papeis;

	}

	public static Usuario criarUsuario() {

		Usuario usuario = new Usuario();
		usuario.setId(1L);
		usuario.setJaCriptografada(true);
		usuario.setLastPasswordReset(Date.valueOf("11/12/2021"));
		usuario.setPapeis(criarPapeis());
		usuario.setPassword("1234");
		usuario.setPessoa(criarPessoa());
		usuario.setUsername("username");
		return usuario;
	}

	public static SolicitacaoCadastro criarSolicitacaoCadastro() {

		SolicitacaoCadastro solicitacaoCadastro = new SolicitacaoCadastro();
		solicitacaoCadastro.setCpfOrientador("000");
		solicitacaoCadastro.setBairro("centro");
		solicitacaoCadastro.setTipoPessoa(TipoPessoa.Fisica);
		solicitacaoCadastro.setNome("pessoa1");
		solicitacaoCadastro.setDocumento("6666");
		solicitacaoCadastro.setCep("1111");
		solicitacaoCadastro.setEndereco("endereço1");
		solicitacaoCadastro.setNumero("224");
		solicitacaoCadastro.setComplemento("na avenida");
		solicitacaoCadastro.setTelefone("49999999");
		solicitacaoCadastro.setCelular("499999999");
		solicitacaoCadastro.setEmail("pessoa1@email.com");
		solicitacaoCadastro.setDataCriacao(LocalDate.now());
		solicitacaoCadastro.setCidade(criarCidade());
		solicitacaoCadastro.setCpfOrientador("");
		solicitacaoCadastro.setDataTerminoProgramaEnsino(LocalDate.MAX);
		solicitacaoCadastro.setEhProfessor(false);
		solicitacaoCadastro.setId(1l);
		solicitacaoCadastro.setInscricaoEstadual("inscrição estadual");
		solicitacaoCadastro.setNomeInstituicao("utfpr");
		solicitacaoCadastro.setSenha("1234");
		solicitacaoCadastro.setPessoa(criarPessoa());
		solicitacaoCadastro.setNomeprojeto("projeto");
		solicitacaoCadastro.setNomeProgramaEnsino("programa de ensino");
		return solicitacaoCadastro;

	}

	public static Pessoa criarPessoa() {

		Pessoa pessoa = new Pessoa();
		pessoa.setId(1l);
		pessoa.setTipoPessoa(TipoPessoa.Fisica);
		pessoa.setNome("pessoa1");
		pessoa.setDocumento("6666");
		pessoa.setCep("1111");
		pessoa.setEndereco("endereço1");
		pessoa.setBairro("centro");
		pessoa.setNumero("224");
		pessoa.setComplemento("na avenida");
		pessoa.setTelefone("49999999");
		pessoa.setCelular("499999999");
		pessoa.setEmail("pessoa1@email.com");
		pessoa.setInscricaoEstadual("ie1");
		pessoa.setDataCriacao(LocalDate.now());
		pessoa.setEhAtivo(true);
		pessoa.setCidade(criarCidade());
		pessoa.setVinculos(criarListaVinculos());
		pessoa.setSolicitacaoCadastro(criarSolicitacaoCadastro());
		pessoa.setUsuario(criarUsuario());
		return pessoa;

	}

	public static ProgramaEnsino criarProgramaEnsino() {

		ProgramaEnsino programaEnsino = ProgramaEnsino.builder().nome("Trabalho de Conclusão de Curso").sigla("TCC")
				.build();

		return programaEnsino;
	}

	public static java.util.Set<Servico> criarServicos() {

		java.util.Set<Servico> servicos;

		servicos = new HashSet<Servico>();

		Servico servico1 = new Servico();
		servico1.setDescricao("descrição 1");

		Servico servico2 = new Servico();
		servico2.setDescricao("descrição 2");

		servicos.add(servico1);
		servicos.add(servico2);

		return servicos;

	}

	public static Equipamento criarEquipamento() {

		Equipamento equipamento = Equipamento.builder().nome("AA").sigla("AAA").metodologia("metodologia")
				.laudoPadrao("laudo padrão").observacoesFixas("observações fixas")
				.unidadeMedidaExterno(UnidadeMedidaCobrancaEquipamento.Amostra).valorInterno(222e10)
				.unidadeMedidaExterno(UnidadeMedidaCobrancaEquipamento.Amostra).valorExterno(111e10)
				.unidadeMedidaPadrao(UnidadeMedidaCobrancaEquipamento.Amostra).valorPadrao(121e10)
				.servicos(criarServicos()).build();

		return equipamento;

	}

	public static CreditoProfessor criarCreditoProfessor() {

		CreditoProfessor creditoProfessor = new CreditoProfessor();
		creditoProfessor.setId(2l);
		creditoProfessor.setValorSaldo(2200d);
		creditoProfessor.setNomeProjeto("nome projeto");

		return creditoProfessor;

	}

	public static Formulario criarFormulario() {
		Formulario formulario = Formulario.builder().pessoa(criarPessoa())
				.participacaoPrograma(criarParticipacaoProgramaEnsino()).equipamento(criarEquipamento())
				.servico(criarServicos()).professor(criarPessoa()).creditoProfessor(criarCreditoProfessor())
				.dataSolicitacao(LocalDate.now()).nomeProgramaEnsino("nome do programa").naturezaProjeto("quimico")
				.nomeServico("analise").metodologia("descricao").laudo("laudo em espera")
				.status(StatusFormulario.AGUARDANDO_ANALISE).motivoRecusaAmostra("motivo")
				.emailEnviadoSolicitante(false).emailEnviadoFuntef(false).analise(false).numeroAmostras(4)
				.imagem("imagem").anexos(null).build();

		return formulario;

	}

	public static ParticipacaoProgramaEnsino criarParticipacaoProgramaEnsino() {

		return ParticipacaoProgramaEnsino.builder().programaEnsino(criarProgramaEnsino())
				.participante(criarPessoaInstituicao()).orientador(criarPessoaInstituicao())
				.dataTermino(LocalDate.of(2024, 12, 1)).ehAtivo(false).build();

	}

}
