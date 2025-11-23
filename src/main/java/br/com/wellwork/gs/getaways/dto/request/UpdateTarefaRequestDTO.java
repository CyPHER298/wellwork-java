package br.com.wellwork.gs.getaways.dto.request;

import br.com.wellwork.gs.domain.enums.EnumStatusTarefa;

import java.time.LocalDateTime;
import java.util.List;

public record UpdateTarefaRequestDTO(
        Integer id,
        String titulo,
        String descricao,
        LocalDateTime dataHora,
        EnumStatusTarefa status,
        List<Integer> usuarioIds
) {
}
