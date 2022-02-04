package br.edu.utfpr.pb.labquimica.backend.controller;


import br.edu.utfpr.pb.labquimica.backend.model.HistoricoCredito;
import br.edu.utfpr.pb.labquimica.backend.model.Papel;
import br.edu.utfpr.pb.labquimica.backend.repository.HistoricoCreditoRepository;
import br.edu.utfpr.pb.labquimica.backend.security.acessor.UserAccessor;
import br.edu.utfpr.pb.labquimica.backend.service.CrudService;
import br.edu.utfpr.pb.labquimica.backend.service.HistoricoCreditoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("historicoCredito")
public class HistoricoCreditoController extends CrudController<HistoricoCredito, Long> {

    @Autowired
    private HistoricoCreditoService historicoCreditoService;
    
    public HistoricoCreditoController( HistoricoCreditoService historicoCreditoService) {
        this.historicoCreditoService =historicoCreditoService;
   }
    
    

  
    private UserAccessor usuarioAccessor;

    public HistoricoCreditoController( UserAccessor usuarioAccessor) {
        this.usuarioAccessor =usuarioAccessor;
   }
    
    
    
    @Override
    protected CrudService<HistoricoCredito, Long> getService() {
        return historicoCreditoService;
    }

    @GetMapping("busca-por-professor")
    public List<HistoricoCredito> findFormulariosPessoa() {
        Papel papel = new Papel();
        papel.setId(1L);
        if(usuarioAccessor.getUsuario().getPapeis().contains(papel)){
            return historicoCreditoService.findAll();
        }
        return historicoCreditoService.findByCreditoprofessorPessoaId(usuarioAccessor.getUsuario().getPessoa().getId());
    }
}
