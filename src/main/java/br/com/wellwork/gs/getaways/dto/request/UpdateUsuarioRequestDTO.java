package br.com.wellwork.gs.getaways.dto.request;

import br.com.wellwork.gs.domain.enums.EnumCargoUsuario;

public record UpdateUsuarioRequestDTO(
        Integer id,
        String email,
        String senha,
        String nome,
        EnumCargoUsuario cargo,
        String acessibilidade
) {

}
