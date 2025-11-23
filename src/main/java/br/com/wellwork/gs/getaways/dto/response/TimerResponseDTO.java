package br.com.wellwork.gs.getaways.dto.response;

import br.com.wellwork.gs.domain.Timer;
import br.com.wellwork.gs.domain.enums.EnumStatusTimer;

import java.time.LocalDateTime;

public record TimerResponseDTO(
        Integer id,
        String tipo,
        Long duracaoSegundos,
        LocalDateTime inicio,
        LocalDateTime fim,
        EnumStatusTimer status,
        Integer usuarioId
) {

    public static TimerResponseDTO fromTimer(Timer timer) {
        Long duracaoSegundos = timer.getDuracao() != null ? timer.getDuracao().getSeconds() : null;

        return new TimerResponseDTO(
                timer.getId(),
                timer.getTipo(),
                duracaoSegundos,
                timer.getInicio(),
                timer.getFim(),
                timer.getStatus(),
                timer.getUsuario() != null ? timer.getUsuario().getId() : null
        );
    }
}
