package br.com.wellwork.gs.getaways.dto.response;

import br.com.wellwork.gs.domain.Meta;

public record MetaResponseDTO(
        Integer id,
        String titulo,
        String descricao,
        Integer usuarioId
) {
    public static MetaResponseDTO fromMeta(Meta meta) {
        return new MetaResponseDTO(
                meta.getId(),
                meta.getTitulo(),
                meta.getDescricao(),
                meta.getUsuario() != null ? meta.getUsuario().getId() : null
        );
    }
}
