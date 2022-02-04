package br.edu.utfpr.pb.labquimica.backend.controller;

import br.edu.utfpr.pb.labquimica.backend.model.ParticipacaoProgramaEnsino;
import br.edu.utfpr.pb.labquimica.backend.service.CrudService;
import br.edu.utfpr.pb.labquimica.backend.service.PapelService;
import br.edu.utfpr.pb.labquimica.backend.service.ParticipacaoProgramaEnsinoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("participacao-programa-ensino")
public class ParticipacaoProgramaEnsinoController extends CrudController<ParticipacaoProgramaEnsino, Long> {

    
    private ParticipacaoProgramaEnsinoService participacaoProgramaEnsinoService;

    public ParticipacaoProgramaEnsinoController(ParticipacaoProgramaEnsinoService participacaoProgramaEnsinoService) {
        this.participacaoProgramaEnsinoService =participacaoProgramaEnsinoService;
   }
    
    @Override
    protected CrudService<ParticipacaoProgramaEnsino, Long> getService() {
        return participacaoProgramaEnsinoService;
    }

    @GetMapping("pessoa-instituicao/{pessoaInstituicaoId}")
    public List<ParticipacaoProgramaEnsino> findParticipacaoPrograma(@PathVariable Long pessoaInstituicaoId){
        return participacaoProgramaEnsinoService.findParticipacaoPrograma(pessoaInstituicaoId);
    }


}
