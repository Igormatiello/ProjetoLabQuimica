package br.edu.utfpr.pb.labquimica.backend.model;

import br.edu.utfpr.pb.labquimica.backend.converter.BooleanConverter;
import br.edu.utfpr.pb.labquimica.backend.utils.DefaultFields;
import br.edu.utfpr.pb.labquimica.backend.utils.ValidationMessages;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = {"id"})
public class Usuario implements UserDetails {

    private static final BCryptPasswordEncoder bCrypts = new BCryptPasswordEncoder(10);
    private static final long serialVersionUID = 3405633164317529200L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = DefaultFields.DESCRICAO, nullable = false)
    @NotBlank(message = ValidationMessages.UserNameNaoPodeSerVazio)
    private String username;

    @Column(length = DefaultFields.SENHA, nullable = false)
    private String password;

    @Column
    private Date lastPasswordReset;

    @Convert(converter = BooleanConverter.class)
    @Column(columnDefinition = "char(1) default 'S'")
    private boolean enabled;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Papel> papeis;

    @OneToOne(optional = true, mappedBy = "usuario")
    private Pessoa pessoa;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return papeis;
    }

    public void addPermissao(Papel papel) {
        if (papeis == null) {
            papeis = new HashSet<>();
        }
        papeis.add(papel);
    }

    public String getEncondedPassword(String pass) {
        if (pass != null && !pass.equals("")) {
            return bCrypts.encode(this.getPassword());
        }
        return pass;
    }

    @PreRemove
    private void removerPapeis() {
        this.papeis.clear();
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    @Transient
    public boolean jaCriptografada;

    @PrePersist
    private void preSave() {
        if (papeis.stream().anyMatch(x -> x.getId() == 3L))
            return;

        if (!jaCriptografada) {
            if (getId() == null || getId() == 0) {
                setPassword(getEncondedPassword(getPassword()));
            }
        }
    }
}
