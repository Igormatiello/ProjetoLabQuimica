package br.edu.utfpr.pb.labquimica.backend.viewmodels;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@ToString
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class ResultadoOperacaoViewModel<T> {
    private boolean sucesso;
    private T corpo;
    private List<String> mensagens;

    public ResultadoOperacaoViewModel()
    {
        mensagens = new ArrayList<>();
    }

    public ResultadoOperacaoViewModel<T> returningFailMessage(String msg){
        this.mensagens.add(msg);
        this.sucesso = false;

        return this;
    }

    public ResultadoOperacaoViewModel<T> addFailMessage(String msg){
        this.mensagens.add(msg);
        this.sucesso = false;

        return this;
    }

    public ResultadoOperacaoViewModel<T> returningSuccess(){
        this.sucesso = true;

        return this;
    }
    public ResultadoOperacaoViewModel<T> returningSuccess(T corpo){
        this.sucesso = true;
        this.corpo = corpo;
        return this;
    }
}
