package br.edu.utfpr.pb.labquimica.backend.service;

import br.edu.utfpr.pb.labquimica.backend.model.Indicadores;

import java.time.LocalDate;


public interface HomeService {



	Indicadores findDadosIndicadores(LocalDate dtIni, LocalDate dtFim);
}
