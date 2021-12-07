package br.edu.utfpr.pb.labquimica.backend.security.acessor;

import br.edu.utfpr.pb.labquimica.backend.model.Usuario;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component()
@Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class UserAccessor {
    private Usuario usuario;

    public Usuario getUsuario() {
        if (usuario == null) {
            var auth = SecurityContextHolder.getContext().getAuthentication();
            if (auth != null && auth.getPrincipal() instanceof Usuario) {
                usuario = (Usuario) auth.getPrincipal();
            }
        }
        return usuario;
    }

    public Long getUsuarioId() {
        var us = Optional.of(getUsuario());
        return us.isEmpty() ? null : us.get().getId();
    }
}
