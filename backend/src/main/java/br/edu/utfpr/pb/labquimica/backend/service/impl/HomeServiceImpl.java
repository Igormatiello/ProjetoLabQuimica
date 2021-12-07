package br.edu.utfpr.pb.labquimica.backend.service.impl;

import br.edu.utfpr.pb.labquimica.backend.enumerators.StatusFormulario;
import br.edu.utfpr.pb.labquimica.backend.enumerators.TipoPessoa;
import br.edu.utfpr.pb.labquimica.backend.model.*;
import br.edu.utfpr.pb.labquimica.backend.service.FormularioService;
import br.edu.utfpr.pb.labquimica.backend.service.HomeService;
import br.edu.utfpr.pb.labquimica.backend.service.SolicitacaoCadastroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class HomeServiceImpl implements HomeService {

    @Autowired
    private FormularioService formularioService;

    @Autowired
    private SolicitacaoCadastroService solicitacaoCadastroService;

    @Override
    public Indicadores findDadosIndicadores(LocalDate dtIni, LocalDate dtFim) {
        Indicadores indicadores = new Indicadores();
        indicadores.setIndicadorCabecalho(getDadosCabecalho(dtIni, dtFim));
        indicadores.setIndicadorSolicitacaoCadastroList(getDadosSolicitacaoCadastro(dtIni, dtFim));
        indicadores.setSolicitacaoCadastrosPendentes(solicitacaoCadastroService.findSolicitacoesAbertas());
        indicadores.setIndicadorFormularioByDayList(getDadosFormulariosByDay(dtIni, dtFim));
        return indicadores;
    }

    private List<IndicadorFormularioByDay> getDadosFormulariosByDay(LocalDate dtIni, LocalDate dtFim) {
        List<IndicadorFormularioByDay> list = formularioService.countAllByDataSolicitacaoBetween(dtIni, dtFim);
        list.forEach(indicadorFormularioByDay -> {
            indicadorFormularioByDay.setDataSolic(indicadorFormularioByDay.getDataSolicitacao().toString());
        });
        return list;
    }

    private List<IndicadorSolicitacaoCadastro> getDadosSolicitacaoCadastro(LocalDate dtIni, LocalDate dtFim) {
        Integer qtdeAluno = 0;
        Integer qtdeEmpresa = 0;
        Integer qtdeProfessor = 0;

        List<SolicitacaoCadastro> list = solicitacaoCadastroService.findAllByDataCriacaoBetween(dtIni, dtFim);
        for (SolicitacaoCadastro solicitacaoCadastro : list) {
            if (solicitacaoCadastro.isEhProfessor()) {
                qtdeProfessor++;
            } else if (!solicitacaoCadastro.isEhProfessor() &&
                    solicitacaoCadastro.getTipoPessoa().equals(TipoPessoa.Fisica)) {
                qtdeAluno++;
            } else {
                qtdeEmpresa++;
            }
        }
        List<IndicadorSolicitacaoCadastro> toReturn = new ArrayList<>();
        if (qtdeAluno > 0) {
            toReturn.add(new IndicadorSolicitacaoCadastro("Aluno", qtdeAluno));
        }
        if (qtdeEmpresa > 0) {
            toReturn.add(new IndicadorSolicitacaoCadastro("Empresa", qtdeEmpresa));
        }
        if (qtdeProfessor > 0) {
            toReturn.add(new IndicadorSolicitacaoCadastro("Professor", qtdeProfessor));
        }

        return toReturn;
    }

    private IndicadorCabecalho getDadosCabecalho(LocalDate dtIni, LocalDate dtFim) {
        List<Formulario> list = formularioService.findAllByDataSolicitacaoBetween(dtIni, dtFim);

        Integer formEmEspera = 0;
        Integer formEmAnalise = 0;
        Integer formFinalizado = 0;

        for (Formulario formulario : list) {
            if (formulario.getStatus().equals(StatusFormulario.AGUARDANDO_ANALISE)) {
                formEmAnalise++;
            } else if (formulario.getStatus().equals(StatusFormulario.FINALIZADO) ||
                    formulario.getStatus().equals(StatusFormulario.AMOSTRA_RECUSADA)) {
                formFinalizado++;
            } else if (formulario.getStatus().equals(StatusFormulario.AGUARDANDO_AMOSTRA)) {
                formEmEspera++;
            }
        }

        return new IndicadorCabecalho(formEmEspera, formEmAnalise, formFinalizado);
    }
}
