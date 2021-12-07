package br.edu.utfpr.pb.labquimica.backend.controller;

import br.edu.utfpr.pb.labquimica.backend.model.Indicadores;
import br.edu.utfpr.pb.labquimica.backend.service.HomeService;
import br.edu.utfpr.pb.labquimica.backend.utils.DateUtil;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("home")
public class HomeController {

    @Autowired
    private HomeService homeService;

    @GetMapping("find-dados-indicadores")
    public Indicadores findDadosIndicadores(@RequestParam("dtIni") String dtIni,
                                            @RequestParam("dtFim") String dtFim) {
        return homeService.findDadosIndicadores(
                LocalDate.parse(dtIni), LocalDate.parse(dtFim) //modificado do original
        );
    }
}
