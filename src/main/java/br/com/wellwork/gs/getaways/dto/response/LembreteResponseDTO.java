package br.com.wellwork.gs.getaways.dto.response;

import br.com.wellwork.gs.domain.Lembrete;
import br.com.wellwork.gs.domain.enums.EnumAtivoLembrete;

public record LembreteResponseDTO(
        Integer id,
        String tipo,
        String sequencia,
        EnumAtivoLembrete ativo
) {
    public static LembreteResponseDTO fromLembrete(Lembrete lembrete) {
        return new LembreteResponseDTO(
                lembrete.getId(),
                lembrete.getTipo(),
                lembrete.getSequencia(),
                lembrete.getAtivo()
        );
    }
}
