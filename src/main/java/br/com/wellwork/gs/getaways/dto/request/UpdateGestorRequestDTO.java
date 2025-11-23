package br.com.wellwork.gs.getaways.dto.request;

public record UpdateGestorRequestDTO(
        Integer id,
        String nome,
        String email,
        String cargo,
        String departamento
) {
}
