package br.com.wellwork.gs.domain;

import br.com.wellwork.gs.domain.enums.EnumCargoUsuario;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@With
@Getter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Usuario implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;
    private String nome;
    private String email;
    private String senha;
    @Enumerated(EnumType.STRING)
    private EnumCargoUsuario cargo;
    private String acessibilidade;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (this.cargo == EnumCargoUsuario.GESTOR) {
            return List.of(new SimpleGrantedAuthority("ROLE_GESTOR"), new SimpleGrantedAuthority("ROLE_USER"));
        } else {
            return List.of(new SimpleGrantedAuthority("ROLE_USER"));
        }
    }

    @Override
    public String getPassword() {
        return senha;
    }

    @Override
    public String getUsername() {
        return email;
    }
}
