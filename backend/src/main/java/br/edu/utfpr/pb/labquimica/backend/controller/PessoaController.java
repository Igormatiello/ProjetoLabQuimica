package br.edu.utfpr.pb.labquimica.backend.controller;

import br.edu.utfpr.pb.labquimica.backend.enumerators.TipoPessoa;
import br.edu.utfpr.pb.labquimica.backend.model.Pessoa;
import br.edu.utfpr.pb.labquimica.backend.repository.PessoaRepository;
import br.edu.utfpr.pb.labquimica.backend.repository.UsuarioRepository;
import br.edu.utfpr.pb.labquimica.backend.security.acessor.UserAccessor;
import br.edu.utfpr.pb.labquimica.backend.service.CrudService;
import br.edu.utfpr.pb.labquimica.backend.service.PessoaService;
import br.edu.utfpr.pb.labquimica.backend.viewmodels.ResultadoOperacaoViewModel;
import br.edu.utfpr.pb.labquimica.backend.viewmodels.SolicitacaoCadastroViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("pessoa")
public class PessoaController extends CrudController<Pessoa, Long> {

    @Autowired
    private PessoaService pessoaService;

    @Autowired
    private PessoaRepository pessoaRepository;

    @Autowired
    private UserAccessor usuarioAccessor;

    @Override
    protected CrudService<Pessoa, Long> getService() {
        return pessoaService;
    }

    @GetMapping("dados-aprovacao/{id}")
    public SolicitacaoCadastroViewModel getDadosAprovacao(@PathVariable Long id) {
        return pessoaService.getDadosValidacaoCadastro(id);
    }

    @GetMapping("doc-exists/{tipoPessoa}/{cpfCnpj}")
    public ResultadoOperacaoViewModel<Object> existsComDocumento(@PathVariable TipoPessoa tipoPessoa,
                                                                 @PathVariable String cpfCnpj) {
        return pessoaService.findCpfCnpjCadastrado(tipoPessoa, cpfCnpj);
    }

    @PostMapping("aprovar-solicitacao")
    public ResultadoOperacaoViewModel<Pessoa> saveAprovacao(@RequestBody @Valid SolicitacaoCadastroViewModel source) {
        var pessoa = pessoaService.saveValidarCadastro(source);
        if (pessoa.isSucesso()) {
            pessoaService.sendEmailRetornoSolicitacao(source.getEmail());
        }
        return pessoa;
    }

    @GetMapping("buscaProfessor")
    public List<Pessoa> buscaProfessores(){
        return pessoaRepository.buscaProfessores();
    }

    @GetMapping("buscaProfessorFormulario/{pessoaInstituicaoId}")
    public List<Pessoa> buscaProfessoresFormulario(@PathVariable Long pessoaInstituicaoId){
        return pessoaRepository.buscaProfessoresFormularios(pessoaInstituicaoId);
    }

    @GetMapping("buscaPessoaIdProfessorLogado")
    public List<Pessoa> buscaPessoaIdProfessorLogado(){
        return pessoaRepository.buscaProfessorByUsuarioId(usuarioAccessor.getUsuarioId());
    }

}
