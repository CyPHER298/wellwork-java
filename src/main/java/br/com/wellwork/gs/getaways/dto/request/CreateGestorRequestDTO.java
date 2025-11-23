package br.com.wellwork.gs.getaways.dto.request;

public record CreateGestorRequestDTO(
        String nome,
        String email,
        String senha,
        String cargo,
        String departamento
) {
}
