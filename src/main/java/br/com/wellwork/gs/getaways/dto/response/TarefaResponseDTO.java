package br.com.wellwork.gs.getaways.dto.response;

import br.com.wellwork.gs.domain.Tarefa;
import br.com.wellwork.gs.domain.enums.EnumStatusTarefa;

import java.time.LocalDateTime;
import java.util.List;

public record TarefaResponseDTO(
        Integer id,
        String titulo,
        String descricao,
        LocalDateTime dataHora,
        EnumStatusTarefa status,
        List<Integer> usuarioIds
) {

    public static TarefaResponseDTO fromTarefa(Tarefa tarefa) {
        List<Integer> usuarioIds = tarefa.getUsuarios() != null
                ? tarefa.getUsuarios().stream().map(u -> u.getId()).toList()
                : List.of();

        return new TarefaResponseDTO(
                tarefa.getId(),
                tarefa.getTitulo(),
                tarefa.getDescricao(),
                tarefa.getDataHora(),
                tarefa.getStatus(),
                usuarioIds
        );
    }
}
