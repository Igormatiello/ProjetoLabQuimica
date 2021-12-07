package br.edu.utfpr.pb.labquimica.backend.service.impl;

import br.edu.utfpr.pb.labquimica.backend.enumerators.TipoPessoa;
import br.edu.utfpr.pb.labquimica.backend.model.SolicitacaoCadastro;
import br.edu.utfpr.pb.labquimica.backend.repository.EquipamentoRepository;
import br.edu.utfpr.pb.labquimica.backend.repository.SolicitacaoCadastroRepository;
import br.edu.utfpr.pb.labquimica.backend.service.ConfEmailService;
import br.edu.utfpr.pb.labquimica.backend.service.EmailService;
import br.edu.utfpr.pb.labquimica.backend.service.EquipamentoService;
import br.edu.utfpr.pb.labquimica.backend.service.SolicitacaoCadastroService;
import br.edu.utfpr.pb.labquimica.backend.utils.Email;
import br.edu.utfpr.pb.labquimica.backend.utils.ValidationMessages;
import br.edu.utfpr.pb.labquimica.backend.validator.ValidaCpfCnpj;
import br.edu.utfpr.pb.labquimica.backend.viewmodels.ResultadoOperacaoViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
public class SolicitacaoCadastroServiceImpl extends CrudServiceImpl<SolicitacaoCadastro, Long> implements SolicitacaoCadastroService {

    @Autowired
    private SolicitacaoCadastroRepository solicitacaoCadastroRepository;

    @Autowired
    private PessoaServiceImpl pessoaService;

    @Autowired
    private EmailService emailService;

    @Override
    protected JpaRepository<SolicitacaoCadastro, Long> getRepository() {
        return solicitacaoCadastroRepository;
    }

    @Transactional(propagation = Propagation.NESTED)
    public ResultadoOperacaoViewModel<SolicitacaoCadastro> saveWithValidation(SolicitacaoCadastro entity) {
        var validate = new ValidaCpfCnpj();
        var result = new ResultadoOperacaoViewModel<SolicitacaoCadastro>();

        if (entity.getTipoPessoa() == TipoPessoa.Fisica) {
            if (!validate.isCPF(entity.getDocumento())) {
                return result.returningFailMessage(ValidationMessages.FormatoCpfInvalido);
            }
        } else {
            if (!validate.isCNPJ(entity.getDocumento())) {
                return result.returningFailMessage(ValidationMessages.FormatoCnpjInvalido);
            }
        }

        var checkCadastrado = pessoaService.findCpfCnpjCadastrado(entity.getTipoPessoa(), entity.getDocumento());
        if (checkCadastrado.isSucesso()) {
            return result.returningFailMessage(ValidationMessages.PessoaJaExiste);
        }

        if (entity.getTipoPessoa() == TipoPessoa.Fisica && !entity.isEhProfessor()) {
            if (!pessoaService.findCpfCnpjCadastrado(TipoPessoa.Fisica, entity.getCpfOrientador()).isSucesso()) {
                return result.returningFailMessage(ValidationMessages.ProfessorNaoCadastrado);
            }
        }
        super.save(entity);

        return result.returningSuccess(entity);
    }

    @Override
    public List<SolicitacaoCadastro> findSolicitacoesAbertas() {
        return solicitacaoCadastroRepository.findSolicitacoesAbertas();
    }

    @Override
    public void sendEmailToResponsavelLiberacao() {
        Email email = new Email()
                .setTitulo("Nova Solicitação de Cadastro")
                .setConteudo("Foi realizado uma nova solicitação de cadastro. Para visualizar, basta acessar o menu Cadastros -> Solicitações de Cadastro.");
        emailService.enviarToEmailPadrao(email);
    }

    @Override
    public List<SolicitacaoCadastro> findAllByDataCriacaoBetween(LocalDate dtIni, LocalDate dtFim) {
        return solicitacaoCadastroRepository.findAllByDataCriacaoBetween(dtIni, dtFim);
    }
}
