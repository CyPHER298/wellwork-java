package br.com.wellwork.gs.getaways.dto.request;

import br.com.wellwork.gs.domain.enums.EnumCargoUsuario;

public record RegisterRequestDTO(
        String nome,
        String email,
        String senha,
        EnumCargoUsuario cargo
) {
}
