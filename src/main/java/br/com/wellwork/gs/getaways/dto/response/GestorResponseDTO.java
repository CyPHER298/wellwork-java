package br.com.wellwork.gs.getaways.dto.response;

import br.com.wellwork.gs.domain.Gestor;

public record GestorResponseDTO(
        Integer id,
        String nome,
        String email,
        String cargo,
        String departamento
) {

    public static GestorResponseDTO fromGestor(Gestor gestor) {
        return new GestorResponseDTO(
                gestor.getId(),
                gestor.getNome(),
                gestor.getEmail(),
                gestor.getCargo(),
                gestor.getDepartamento()
        );
    }
}
