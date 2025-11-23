package br.com.wellwork.gs.getaways.dto.request;

import br.com.wellwork.gs.domain.enums.EnumStatusAlerta;

import java.time.LocalDateTime;

public record CreateAlertaCriseRequestDTO(
        Integer usuarioId,
        Integer gestorId,
        LocalDateTime dataHora,
        EnumStatusAlerta status
) {
}
