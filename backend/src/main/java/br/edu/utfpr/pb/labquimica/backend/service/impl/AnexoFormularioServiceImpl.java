package br.edu.utfpr.pb.labquimica.backend.service.impl;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import br.edu.utfpr.pb.labquimica.backend.model.AnexoFormulario;
import br.edu.utfpr.pb.labquimica.backend.repository.AnexoFormularioRepository;
import br.edu.utfpr.pb.labquimica.backend.service.AnexoFormularioService;

@Service
public class AnexoFormularioServiceImpl extends CrudServiceImpl<AnexoFormulario, Long>
		implements AnexoFormularioService {

	private AnexoFormularioRepository anexoFormularioRepository;

	public AnexoFormularioServiceImpl(AnexoFormularioRepository anexoFormularioRepository) {
		this.anexoFormularioRepository = anexoFormularioRepository;
	}

	@Override
	protected JpaRepository<AnexoFormulario, Long> getRepository() {
		return anexoFormularioRepository;
	}
}
