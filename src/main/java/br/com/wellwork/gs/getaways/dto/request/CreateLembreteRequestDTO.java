package br.com.wellwork.gs.getaways.dto.request;

import br.com.wellwork.gs.domain.enums.EnumAtivoLembrete;

public record CreateLembreteRequestDTO(
        String tipo,
        String sequencia,
        EnumAtivoLembrete ativo
) {
}
