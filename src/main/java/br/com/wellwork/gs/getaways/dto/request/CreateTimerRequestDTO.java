package br.com.wellwork.gs.getaways.dto.request;

import br.com.wellwork.gs.domain.enums.EnumStatusTimer;

import java.time.LocalDateTime;

public record CreateTimerRequestDTO(
        String tipo,
        Long duracaoSegundos,
        LocalDateTime inicio,
        LocalDateTime fim,
        EnumStatusTimer status,
        Integer usuarioId
) {
}
