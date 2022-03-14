package br.edu.utfpr.pb.labquimica.backend.controller;

import java.time.LocalDate;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.edu.utfpr.pb.labquimica.backend.model.Indicadores;
import br.edu.utfpr.pb.labquimica.backend.service.HomeService;
import lombok.NoArgsConstructor;

@RestController
@RequestMapping("home")
@NoArgsConstructor
public class HomeController {

	private HomeService homeService;

	public HomeController(HomeService homeService) {
		this.homeService = homeService;
	}

	@GetMapping("find-dados-indicadores")
	public Indicadores findDadosIndicadores(@RequestParam("dtIni") String dtIni, @RequestParam("dtFim") String dtFim) {
		return homeService.findDadosIndicadores(LocalDate.parse(dtIni), LocalDate.parse(dtFim) // modificado do original
		);
	}
}
