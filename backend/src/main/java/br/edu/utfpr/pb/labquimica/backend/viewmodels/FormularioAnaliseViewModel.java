package br.edu.utfpr.pb.labquimica.backend.viewmodels;

import br.edu.utfpr.pb.labquimica.backend.enumerators.StatusFormulario;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(of = "id")
public class FormularioAnaliseViewModel {

    private Long formularioId;

    private StatusFormulario status;

    private String motivoRejeicao;

    public Long getFormularioId() {
        return formularioId;
    }

    public FormularioAnaliseViewModel setFormularioId(Long formularioId) {
        this.formularioId = formularioId;
        return this;
    }

    public StatusFormulario getStatus() {
        return status;
    }

    public void setStatus(StatusFormulario status) {
        this.status = status;
    }

    public String getMotivoRejeicao() {
        return motivoRejeicao;
    }

    public void setMotivoRejeicao(String motivoRejeicao) {
        this.motivoRejeicao = motivoRejeicao;
    }
}
