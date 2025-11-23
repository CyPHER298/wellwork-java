package br.com.wellwork.gs.service;

import br.com.wellwork.gs.domain.Tarefa;
import br.com.wellwork.gs.domain.Usuario;
import br.com.wellwork.gs.domain.enums.EnumStatusTarefa;
import br.com.wellwork.gs.getaways.dto.request.CreateTarefaRequestDTO;
import br.com.wellwork.gs.getaways.dto.response.TarefaResponseDTO;
import br.com.wellwork.gs.getaways.repository.TarefaRepository;
import br.com.wellwork.gs.getaways.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TarefaService {

    private final TarefaRepository tarefaRepository;
    private final UsuarioRepository usuarioRepository;

    public Page<Tarefa> listarTarefa(int page, int quantidadeListada, Sort.Direction direction) {
        Pageable pageable = PageRequest.of(
                page, quantidadeListada, Sort.by(direction, "dataHora")
        );
        return tarefaRepository.findAll(pageable);
    }

    @Transactional
    public TarefaResponseDTO criarTarefa(CreateTarefaRequestDTO body) {
        List<Usuario> usuarios = List.of();
        if (body.usuarioIds() != null && !body.usuarioIds().isEmpty()) {
            usuarios = usuarioRepository.findAllById(body.usuarioIds());
        }

        Tarefa tarefa = new Tarefa();
        tarefa.setTitulo(body.titulo());
        tarefa.setDescricao(body.descricao());
        tarefa.setDataHora(body.dataHora());
        tarefa.setStatus(body.status());
        tarefa.setUsuarios(usuarios);

        tarefaRepository.save(tarefa);
        return TarefaResponseDTO.fromTarefa(tarefa);
    }

    @Transactional
    public TarefaResponseDTO alterarTarefa(
            int id,
            String titulo,
            String descricao,
            java.time.LocalDateTime dataHora,
            EnumStatusTarefa status,
            List<Integer> usuarioIds
    ) {
        Tarefa tarefa = tarefaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tarefa não encontrada"));

        if (titulo != null) tarefa.setTitulo(titulo);
        if (descricao != null) tarefa.setDescricao(descricao);
        if (dataHora != null) tarefa.setDataHora(dataHora);
        if (status != null) tarefa.setStatus(status);

        if (usuarioIds != null) {
            List<Usuario> usuarios = usuarioRepository.findAllById(usuarioIds);
            tarefa.setUsuarios(usuarios);
        }

        tarefaRepository.save(tarefa);
        return TarefaResponseDTO.fromTarefa(tarefa);
    }

    @Transactional
    public TarefaResponseDTO deletarTarefa(int id) {
        Tarefa tarefa = tarefaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tarefa não encontrada"));
        tarefaRepository.delete(tarefa);
        return TarefaResponseDTO.fromTarefa(tarefa);
    }

    public TarefaResponseDTO buscarPorId(int id) {
        Tarefa tarefa = tarefaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tarefa não encontrada"));
        return TarefaResponseDTO.fromTarefa(tarefa);
    }
}
