package br.edu.utfpr.pb.labquimica.backend.service.impl;

import br.edu.utfpr.pb.labquimica.backend.enumerators.TipoPessoa;
import br.edu.utfpr.pb.labquimica.backend.model.*;
import br.edu.utfpr.pb.labquimica.backend.repository.InstituicaoRepository;
import br.edu.utfpr.pb.labquimica.backend.repository.PessoaRepository;
import br.edu.utfpr.pb.labquimica.backend.repository.ProgramaEnsinoRepository;
import br.edu.utfpr.pb.labquimica.backend.service.EmailService;
import br.edu.utfpr.pb.labquimica.backend.service.PessoaService;
import br.edu.utfpr.pb.labquimica.backend.utils.Email;
import br.edu.utfpr.pb.labquimica.backend.utils.StringUtils;
import br.edu.utfpr.pb.labquimica.backend.utils.ValidationMessages;
import br.edu.utfpr.pb.labquimica.backend.viewmodels.ResultadoOperacaoViewModel;
import br.edu.utfpr.pb.labquimica.backend.viewmodels.SolicitacaoCadastroViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.HashSet;

@Service
public class PessoaServiceImpl extends CrudServiceImpl<Pessoa, Long> implements PessoaService {
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

    @Override
    protected JpaRepository<Pessoa, Long> getRepository() {
        return pessoaRepository;
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public ResultadoOperacaoViewModel<Pessoa> saveValidarCadastro(SolicitacaoCadastroViewModel solicitacao) {
        var result = new ResultadoOperacaoViewModel<Pessoa>();
        var pessoa = new Pessoa();
        var soliciSalva = solicitacaoCadastroService.findOne(solicitacao.getId());

        // VALIDA OS DADOS DE ENTRADA
        if (!valida(solicitacao, result)) {
            return result;
        }
        // SALVA O USUÁRIO
        var set = new HashSet<Papel>();
        set.add(papelService.findOne(2L));

        var user = new Usuario();
        user.setUsername(solicitacao.getEmail());
        user.setEnabled(true);
        user.setPassword(soliciSalva.getSenha());
        user.setPapeis(set);
        user.setJaCriptografada(true);
        usuarioService.save(user);

        if (user.getId() == 0) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return result.returningFailMessage(ValidationMessages.NaoFoiPossivelFinalizarAOperacao);
        }

        pessoa.setTipoPessoa(solicitacao.getTipoPessoa());
        pessoa.setNome(solicitacao.getNome());
        pessoa.setCelular(solicitacao.getCelular());
        pessoa.setCep(StringUtils.getNumbers(solicitacao.getCep()));
        pessoa.setCidade(solicitacao.getCidade());
        pessoa.setComplemento(solicitacao.getComplemento());
        pessoa.setDocumento(solicitacao.getDocumento());
        pessoa.setEndereco(solicitacao.getEndereco());
        pessoa.setEhAtivo(true);
        pessoa.setEmail(solicitacao.getEmail());
        pessoa.setNumero(solicitacao.getNumero());
        pessoa.setUsuario(user);
        pessoa.setSolicitacaoCadastro(soliciSalva);
        pessoa.setBairro(solicitacao.getBairro());
        pessoa.setTelefone(solicitacao.getTelefone());

        // SALVA A PESSOA
        save(pessoa);

        if (pessoa.getId() == 0) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return result.returningFailMessage(ValidationMessages.NaoFoiPossivelFinalizarAOperacao);
        }

        // SALVA O PESSOA INSTITUIÇÃO SE FOR PESSOA FÍSICA
        if (pessoa.getTipoPessoa() == TipoPessoa.Fisica) {
            var pessoaInstituicao = new PessoaInstituicao();

            pessoaInstituicao.setInstituicao(solicitacao.getInstituicao());
            pessoaInstituicao.setPessoa(pessoa);
            pessoaInstituicao.setCreditoVigente(0.0);
            pessoaInstituicao.setEhAtivo(true);
            pessoaInstituicao.setEhProfessor(solicitacao.isEhProfessor());

            pessoaInstituicaoService.save(pessoaInstituicao);

            if (pessoaInstituicao.getId() == 0) {
                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                return result.returningFailMessage(ValidationMessages.NaoFoiPossivelFinalizarAOperacao);
            }

            var participacaoProgramaEnsino = new ParticipacaoProgramaEnsino();
            if (solicitacao.isEhProfessor()) {

                participacaoProgramaEnsino.setOrientador(pessoaInstituicao);
                participacaoProgramaEnsino.setProgramaEnsino(solicitacao.getProgramaEnsino());
                participacaoProgramaEnsino.setEhAtivo(true);
                participacaoProgramaEnsino.setDataTermino(solicitacao.getDataTerminoPrograma());
                participacaoProgramaEnsino.setParticipante(null);

                participacaoProgramaEnsinoService.save(participacaoProgramaEnsino);
            } else {
                participacaoProgramaEnsino = new ParticipacaoProgramaEnsino();
                participacaoProgramaEnsino.setParticipante(pessoaInstituicao);
                participacaoProgramaEnsino.setOrientador(solicitacao.getOrientador());
                participacaoProgramaEnsino.setEhAtivo(true);
                participacaoProgramaEnsino.setProgramaEnsino(solicitacao.getProgramaEnsino());
                participacaoProgramaEnsino.setDataTermino(solicitacao.getDataTerminoPrograma());
                participacaoProgramaEnsinoService.save(participacaoProgramaEnsino);
            }
        }

        return result.returningSuccess(pessoa);
    }

    private boolean valida(SolicitacaoCadastroViewModel solicitacao, ResultadoOperacaoViewModel<Pessoa> result) {
        if (solicitacao.getTipoPessoa() == TipoPessoa.Fisica) {
            if (solicitacao.getInstituicao().getId() == null) {
                result.addFailMessage(ValidationMessages.InstituicaoNaoPodeSerVazio);
            }
            if (solicitacao.getProgramaEnsino() == null || solicitacao.getProgramaEnsino().getId() == 0) {
                result.addFailMessage(ValidationMessages.ProgramaEnsinoNaoPodeSerVazio);
            }
            if (!solicitacao.isEhProfessor() && (solicitacao.getOrientador() == null || solicitacao.getOrientador().getId() == 0)) {
                result.addFailMessage(ValidationMessages.OrientadorNaoPodeSerVazio);
            }

            if (solicitacao.getDataTerminoPrograma() == null) {
                result.addFailMessage(ValidationMessages.DataTerminoProgramaEnsinoNaoPodeSerVazia);
            }
        } else {
            if (solicitacao.getInscricaoEstadual() == null || solicitacao.getInscricaoEstadual().isEmpty()) {
                result.addFailMessage(ValidationMessages.InscricaoEstadualNaoPodeSerVazia);
            }
        }

        if (pessoaRepository.existsBySolicitacaoCadastroId(solicitacao.getId())) {
            result.addFailMessage(ValidationMessages.SolicitacaoCadastroJaAprovada);
        }

        if (result.getMensagens().isEmpty()) {
            result.setSucesso(true);
        } else {
            result.setSucesso(false);
        }
        return result.isSucesso();
    }

    @Override
    public SolicitacaoCadastroViewModel getDadosValidacaoCadastro(long idSolicitacaoCadastro) {
        var solicitacao = solicitacaoCadastroService.findOne(idSolicitacaoCadastro);

        if (solicitacao == null) {
            return null;
        }

        var solicitacaoView = new SolicitacaoCadastroViewModel(
                solicitacao.getId(),
                solicitacao.getTipoPessoa(),
                solicitacao.getNome(),
                solicitacao.getCep(),
                solicitacao.getEndereco(),
                solicitacao.getBairro(),
                solicitacao.getNumero(),
                solicitacao.getComplemento(),
                solicitacao.getTelefone(),
                solicitacao.getCelular(),
                solicitacao.getEmail(),
                solicitacao.getCidade(),
                solicitacao.getDataCriacao(),
                solicitacao.getDocumento(),
                solicitacao.isEhProfessor(),
                solicitacao.getNomeInstituicao(),
                solicitacao.getNomeProgramaEnsino(),
                solicitacao.getDataTerminoProgramaEnsino(),
                solicitacao.getCpfOrientador(),
                solicitacao.getNomeprojeto()
        );

        if (solicitacao.getNomeInstituicao() != null && !solicitacao.getNomeInstituicao().isEmpty()) {
            solicitacaoView.setInstituicao(
                    instituicaoRepository.findByNome(solicitacao.getNomeInstituicao()));
        }

        if (solicitacao.getNomeProgramaEnsino() != null && !solicitacao.getNomeProgramaEnsino().isEmpty()) {
            solicitacaoView.setProgramaEnsino(
                    programaEnsinoRepository.findByNomeOrSigla(solicitacao.getNomeProgramaEnsino(), solicitacao.getNomeProgramaEnsino())
            );
        }

        if (solicitacao.getTipoPessoa() == TipoPessoa.Fisica && !solicitacao.isEhProfessor()) {
            solicitacaoView.setOrientador(
                    pessoaInstituicaoService.findByInstituicaoNomeAndPessoaDocumentoAndEhProfessorTrue(
                            solicitacao.getNomeInstituicao(),
                            StringUtils.getNumbers(solicitacao.getCpfOrientador())
                    )
            );
            if (solicitacaoView.getOrientador() != null) {
                solicitacaoView.getOrientador().setInstituicao(null);
            }
        }
        return solicitacaoView;
    }

    @Override
    public ResultadoOperacaoViewModel findCpfCnpjCadastrado(TipoPessoa t, String cpfCnpj) {
        var result = new ResultadoOperacaoViewModel<>();

        var exists = pessoaRepository.existsByDocumento(cpfCnpj);

        if (!exists) {
            return result.returningFailMessage(t == TipoPessoa.Fisica ? ValidationMessages.CpfNaoExiste : ValidationMessages.CnpjNaoExiste);
        }

        return result.returningSuccess();
    }

    @Override
    public void sendEmailRetornoSolicitacao(String emailTo) {
        Email email = new Email()
                .setTitulo("Retorno Solicitação de Cadastro")
                .setPara(emailTo)
                .setConteudo("Sua solicitação de cadastro foi aprovada. Você já pode utilizar o sistema.");
        emailService.enviar(email);
    }
}
