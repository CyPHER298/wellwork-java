package br.com.wellwork.gs.getaways.dto.request;

public record UpdateMetaRequestDTO(
        Integer id,
        String titulo,
        String descricao
) {
}
