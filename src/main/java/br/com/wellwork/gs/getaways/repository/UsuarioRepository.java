package br.com.wellwork.gs.getaways.repository;

import br.com.wellwork.gs.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    UserDetails findByEmail(String email);

    boolean existsByEmail(String email);
}
