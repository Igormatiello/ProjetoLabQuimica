package br.edu.utfpr.pb.labquimica.backend.testes.service;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import br.edu.utfpr.pb.labquimica.backend.enumerators.TipoPessoa;
import br.edu.utfpr.pb.labquimica.backend.model.Pessoa;
import br.edu.utfpr.pb.labquimica.backend.model.SolicitacaoCadastro;
import br.edu.utfpr.pb.labquimica.backend.repository.InstituicaoRepository;
import br.edu.utfpr.pb.labquimica.backend.repository.PessoaRepository;
import br.edu.utfpr.pb.labquimica.backend.repository.ProgramaEnsinoRepository;
import br.edu.utfpr.pb.labquimica.backend.repository.SolicitacaoCadastroRepository;
import br.edu.utfpr.pb.labquimica.backend.service.EmailService;
import br.edu.utfpr.pb.labquimica.backend.service.SolicitacaoCadastroService;
import br.edu.utfpr.pb.labquimica.backend.service.impl.PapelServiceImpl;
import br.edu.utfpr.pb.labquimica.backend.service.impl.ParticipacaoProgramaEnsinoServiceImpl;
import br.edu.utfpr.pb.labquimica.backend.service.impl.PessoaInstituicaoServiceImpl;
import br.edu.utfpr.pb.labquimica.backend.service.impl.PessoaServiceImpl;
import br.edu.utfpr.pb.labquimica.backend.service.impl.SolicitacaoCadastroServiceImpl;
import br.edu.utfpr.pb.labquimica.backend.service.impl.UsuarioServiceImpl;
import br.edu.utfpr.pb.labquimica.backend.viewmodels.ResultadoOperacaoViewModel;
import br.edu.utfpr.pb.labquimica.backend.viewmodels.SolicitacaoCadastroViewModel;
import br.edu.utfpr.pb.labquimica.backend.testes.modelRepository.PessoaRepositoryTests;
import br.edu.utfpr.pb.labquimica.backend.testes.modelRepository.PessoalInstituicaoRepositoryTests;
import br.edu.utfpr.pb.labquimica.backend.testes.modelRepository.SolicitacaoCadastroRepositoryTests;
import br.edu.utfpr.pb.labquimica.backend.utils.ValidationMessages;

@RunWith(SpringRunner.class)
@ActiveProfiles("test")




	
public class PessoaServiceTests {
	
	@SpyBean
	PessoaServiceImpl service;
	
	@MockBean
	PessoaRepository repository;
	
	PessoaRepositoryTests PessoaTest;
	
	SolicitacaoCadastroRepositoryTests SolicitacaoTest;
	
	SolicitacaoCadastroRepository SolicitacaoRepository;
	
	@Autowired
    private PessoaRepository pessoaRepository;
    @Autowired
    private UsuarioServiceImpl usuarioService;
    @Autowired
    private InstituicaoRepository instituicaoRepository;
    @Autowired
    private ProgramaEnsinoRepository programaEnsinoRepository;
    @Autowired
    private PessoaInstituicaoServiceImpl pessoaInstituicaoService;
    @Autowired
    private SolicitacaoCadastroServiceImpl solicitacaoCadastroService;
    @Autowired
    private ParticipacaoProgramaEnsinoServiceImpl participacaoProgramaEnsinoService;
    @Autowired
    private PapelServiceImpl papelService;
    @Autowired
    private EmailService emailService;

	
	//@Test
	//public void salvaEValidaUmaPessoa() {
		
		//SolicitacaoCadastro SC= SolicitacaoCadastroRepositoryTests.criarSolicitacaoCadastro();
		
		//SolicitacaoCadastroViewModel len= 
		
		
		//ResultadoOperacaoViewModel<> var = service.saveValidarCadastro(SC);
		
//	}
	
  
    
    
    
	@Test
	public void deveRetornarFalsoQuandoNaoTiverSolicitacao() {
	
		var resultado= new ResultadoOperacaoViewModel<Pessoa>();
		
		
		SolicitacaoCadastroViewModel sc= new SolicitacaoCadastroViewModel();
		sc.setEmail("email");
		sc.setp
		
		sc.setBairro("");
		
		
		SolicitacaoCadastroViewModel resultado =service.getDadosValidacaoCadastro(0);
		
		 
		 Assertions.assertThat(resultado).isNull();
		
		
	}
	@Test
	public void deveValidarSolicitacao() {
	
		SolicitacaoCadastro sc= SolicitacaoCadastroRepositoryTests.criarSolicitacaoCadastro();
		sc.setId(1l);
		
		
		
		 SolicitacaoCadastroViewModel resultado =service.getDadosValidacaoCadastro(1l);
		
	
		 
		 Assertions.assertThat(repository.findById(1l)).isNotNull();
		 Assertions.assertThat(resultado).isNotNull();
	
	}
	@Test
	public void deveEncontrarPessoaPeloDocumento() {
		
		Pessoa pessoa=  PessoaTest.criarPessoa();
		pessoa.setDocumento("12");
		pessoa.setTipoPessoa(TipoPessoa.Fisica);
		
		repository.save(pessoa);
		
		ResultadoOperacaoViewModel resultado= service.findCpfCnpjCadastrado(TipoPessoa.Fisica, "12");
		
		Assertions.assertThat(resultado).isNotNull();
	
		
		
	}

	
	@Test
	public void naoDeveEncontrarPessoaPeloDocumento() {
		
		Pessoa pessoa=  PessoaTest.criarPessoa();
		pessoa.setDocumento("12");
		pessoa.setTipoPessoa(TipoPessoa.Fisica);
		
		repository.save(pessoa);
		
		ResultadoOperacaoViewModel resultado= service.findCpfCnpjCadastrado(TipoPessoa.Juridica, "11");
		
	
		
		
		Assertions.assertThat(resultado).returns(ValidationMessages.CpfNaoExiste);		
	}
	
	@Test
	public void validaCadastro() {
		
		
		SolicitacaoCadastro solicitacaoCadastro= SolicitacaoTest.criarSolicitacaoCadastro();
		solicitacaoCadastro.setId(1l);
		solicitacaoCadastro.setNomeInstituicao("ufpr");
		solicitacaoCadastro.setNomeProgramaEnsino("programa ensino");
		solicitacaoCadastro.setEhProfessor(true);
		
		SolicitacaoRepository.save(solicitacaoCadastro);
		
		SolicitacaoCadastroViewModel resultado= service.getDadosValidacaoCadastro(1l);
		
		
		Assertions.assertThat(resultado.getNomeInstituicao()).isEqualTo("ufpr");
		Assertions.assertThat(resultado.getNomeProgramaEnsino()).isEqualTo("programa ensino");
	
		
	}
	
	
}
