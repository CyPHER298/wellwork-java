package br.com.wellwork.gs.getaways.dto.request;

import br.com.wellwork.gs.domain.enums.EnumStatusAlerta;

import java.time.LocalDateTime;

public record UpdateAlertaCriseRequestDTO(
        Integer id,
        LocalDateTime dataHora,
        EnumStatusAlerta status
) {
}
