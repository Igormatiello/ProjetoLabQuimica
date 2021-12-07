package br.edu.utfpr.pb.labquimica.backend.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "METODO INDISPONIVEL")
public class NotImplementedException extends RuntimeException {
    @Override
    public String getMessage() {
        return "Este método não está disponível para uso";
    }
}