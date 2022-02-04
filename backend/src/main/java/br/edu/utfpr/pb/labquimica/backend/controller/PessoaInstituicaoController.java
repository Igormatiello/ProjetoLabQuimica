package br.edu.utfpr.pb.labquimica.backend.controller;

import br.edu.utfpr.pb.labquimica.backend.model.ParticipacaoProgramaEnsino;
import br.edu.utfpr.pb.labquimica.backend.model.PessoaInstituicao;
import br.edu.utfpr.pb.labquimica.backend.model.Usuario;
import br.edu.utfpr.pb.labquimica.backend.security.acessor.UserAccessor;
import br.edu.utfpr.pb.labquimica.backend.service.CrudService;
import br.edu.utfpr.pb.labquimica.backend.service.ParticipacaoProgramaEnsinoService;
import br.edu.utfpr.pb.labquimica.backend.service.PessoaInstituicaoService;
import br.edu.utfpr.pb.labquimica.backend.service.PessoaService;
import br.edu.utfpr.pb.labquimica.backend.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("pessoa-instituicao")
public class PessoaInstituicaoController extends CrudController<PessoaInstituicao, Long> {

    
    private PessoaInstituicaoService pessoaInstituicaoService;
    
    public  PessoaInstituicaoController (PessoaInstituicaoService pessoaInstituicaoService) {
        this.pessoaInstituicaoService =pessoaInstituicaoService;
   }
    
   
    private UserAccessor usuarioAccessor;
    
    public  PessoaInstituicaoController (UserAccessor usuarioAccessor) {
        this.usuarioAccessor =usuarioAccessor;
   }
    
   
    private ParticipacaoProgramaEnsinoService participacaoProgramaEnsinoService;
    
    public  PessoaInstituicaoController (ParticipacaoProgramaEnsinoService participacaoProgramaEnsinoService) {
        this.participacaoProgramaEnsinoService =participacaoProgramaEnsinoService;
   }

    @Override
    protected CrudService<PessoaInstituicao, Long> getService() {
        return pessoaInstituicaoService;
    }

    @GetMapping("instituicao/{instituicaoId}")
    public List<PessoaInstituicao> findByInstituicao(@PathVariable Integer instituicaoId) {
        return pessoaInstituicaoService.findPessoaInstituicao(usuarioAccessor.getUsuarioId(), instituicaoId);
    }

    @GetMapping("instituicao-sem-usuario/{instituicaoId}")
    public List<PessoaInstituicao> findByInstituicaoSemUsuario(@PathVariable Integer instituicaoId) {
        return pessoaInstituicaoService.findByInstituicao(instituicaoId);
    }

    @GetMapping("instituicoes/{pessoaId}")
    public List<PessoaInstituicao> findByPessoaId(@PathVariable Long pessoaId) {
        return pessoaInstituicaoService.findByPessoaId(pessoaId);
    }

    @Override
    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id) {
        for (ParticipacaoProgramaEnsino programa:participacaoProgramaEnsinoService.findParticipacaoPrograma(id)) {
            participacaoProgramaEnsinoService.delete(programa);
        }
        try {
            getService().delete(id);
        } catch (Exception ex) {

        }

    }
}
