package br.edu.utfpr.pb.labquimica.backend.service.impl;

import br.edu.utfpr.pb.labquimica.backend.model.AnexoFormulario;
import br.edu.utfpr.pb.labquimica.backend.repository.AnexoFormularioRepository;
import br.edu.utfpr.pb.labquimica.backend.service.AnexoFormularioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class AnexoFormularioServiceImpl extends CrudServiceImpl<AnexoFormulario, Long> implements AnexoFormularioService {
    @Autowired
    private AnexoFormularioRepository anexoFormularioRepository;

    @Override
    protected JpaRepository<AnexoFormulario, Long> getRepository() {
        return anexoFormularioRepository;
    }
}