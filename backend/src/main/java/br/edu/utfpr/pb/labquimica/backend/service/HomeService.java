package br.edu.utfpr.pb.labquimica.backend.service;

import br.edu.utfpr.pb.labquimica.backend.model.Indicadores;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public interface HomeService {

    Indicadores findDadosIndicadores(DateTimeFormatter dateTimeFormatter, DateTimeFormatter dateTimeFormatter1);
}
