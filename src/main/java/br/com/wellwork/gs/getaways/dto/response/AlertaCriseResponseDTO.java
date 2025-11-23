package br.com.wellwork.gs.getaways.dto.response;

import br.com.wellwork.gs.domain.AlertaCrise;
import br.com.wellwork.gs.domain.enums.EnumStatusAlerta;

import java.time.LocalDateTime;

public record AlertaCriseResponseDTO(
        Integer id,
        Integer usuarioId,
        Integer gestorId,
        LocalDateTime dataHora,
        EnumStatusAlerta status
) {
    public static AlertaCriseResponseDTO fromAlertaCrise(AlertaCrise alerta) {
        return new AlertaCriseResponseDTO(
                alerta.getId(),
                alerta.getUsuario() != null ? alerta.getUsuario().getId() : null,
                alerta.getGestor() != null ? alerta.getGestor().getId() : null,
                alerta.getDataHora(),
                alerta.getStatus()
        );
    }
}
