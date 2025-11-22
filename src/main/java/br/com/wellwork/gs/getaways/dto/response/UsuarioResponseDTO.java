package br.com.wellwork.gs.getaways.dto.response;

import br.com.wellwork.gs.domain.Usuario;
import br.com.wellwork.gs.domain.enums.EnumCargoUsuario;

public record UsuarioResponseDTO(String nome, String email, EnumCargoUsuario cargo, String acessibilidade) {

    public static UsuarioResponseDTO fromUsuario(Usuario usuario) {
        return new UsuarioResponseDTO(
                usuario.getNome(),
                usuario.getEmail(),
                usuario.getCargo(),
                usuario.getAcessibilidade()
        );
    }
}
