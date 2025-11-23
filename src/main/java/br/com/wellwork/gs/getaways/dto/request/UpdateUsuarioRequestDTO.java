package br.com.wellwork.gs.getaways.dto.request;

public record UpdateUsuarioRequestDTO(
        int id,
        String email,
        String senha,
        String nome
) {
}
