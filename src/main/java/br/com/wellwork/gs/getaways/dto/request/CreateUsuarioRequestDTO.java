package br.com.wellwork.gs.getaways.dto.request;

import br.com.wellwork.gs.domain.enums.EnumCargoUsuario;

public record CreateUsuarioRequestDTO(
        String email,
        String senha,
        EnumCargoUsuario cargo,
        String acessibilidade,
        String nome
) {
}
