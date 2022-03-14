package br.edu.utfpr.pb.labquimica.backend.testes.controller;

import java.util.ArrayList;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.mockito.Mockito;

import br.edu.utfpr.pb.labquimica.backend.controller.CidadeController;
import br.edu.utfpr.pb.labquimica.backend.model.Cidade;
import br.edu.utfpr.pb.labquimica.backend.repository.CidadeRepository;
import br.edu.utfpr.pb.labquimica.backend.service.impl.CidadeServiceImpl;
import br.edu.utfpr.pb.labquimica.backend.testes.modelRepository.CriarObjetos;

public class CidadeControllerTests {

	CidadeController controller;

	CidadeRepository repository;

	CidadeServiceImpl cidadeService;

	@Test
	public void deveRetornarUmaListaDeCidadePeloNome() {

		List<Cidade> listaCidades1 = new ArrayList<>();

		Cidade cidade1 = CriarObjetos.criarCidade();
		String nome = "pato branco";

		cidade1.setNome(nome);
		String nome1 = cidade1.getNome();

		Cidade cidade2 = CriarObjetos.criarCidade2();

		cidade2.setNome(nome);
		String nome2 = cidade2.getNome();

		repository.save(cidade1);
		repository.save(cidade2);

		listaCidades1.add(cidade1);
		listaCidades1.add(cidade2);

		// Mockito.when(controller.findByNome(nome);

		// OfGivenMethod(controller.findByNome(null));

		Mockito.doReturn(listaCidades1).when(controller.findByNome(null));

		// ResponseEntity<List<Cidade>> resultado= controller.findByNome(Optional
		// <nome>);

		// Assertions.assertThat(resultado);

	}

	@Test
	public void deveRetornarTodasAsListasDeCidades() {

		List<Cidade> listaCidades1 = new ArrayList<>();

		Cidade cidade1 = CriarObjetos.criarCidade();

		Cidade cidade2 = CriarObjetos.criarCidade2();

		repository.save(cidade1);
		repository.save(cidade2);

		listaCidades1.add(cidade1);
		listaCidades1.add(cidade2);

		// List<Cidade>resultado= controller.findByNome(Optional <>);

		// Assertions.assertThat(resultado);

	}

}
