package br.edu.utfpr.pb.labquimica.backend.controller;

import br.edu.utfpr.pb.labquimica.backend.model.Equipamento;
import br.edu.utfpr.pb.labquimica.backend.repository.CreditoProfessorRepository;
import br.edu.utfpr.pb.labquimica.backend.service.CrudService;
import br.edu.utfpr.pb.labquimica.backend.service.EquipamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("equipamento")
public class EquipamentoController extends CrudController<Equipamento, Integer> {

  
    private EquipamentoService equipamentoService;

    public EquipamentoController(EquipamentoService equipamentoService) {
        this.equipamentoService =equipamentoService;
        
   }
    
    
    @Override
    protected CrudService<Equipamento, Integer> getService() {
        return equipamentoService;
    }
}
