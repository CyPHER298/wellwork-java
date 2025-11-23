package br.com.wellwork.gs.getaways.dto.request;

public record UpdateUsuarioRequestDTO(
        Integer id,
        String email,
        String senha,
        String nome
) {
}
