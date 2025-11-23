package br.com.wellwork.gs.getaways.dto.request;

public record CreateMetaRequestDTO(
        String titulo,
        String descricao,
        Integer idUsuario
) {
}
