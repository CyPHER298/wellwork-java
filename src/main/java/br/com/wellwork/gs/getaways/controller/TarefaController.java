package br.com.wellwork.gs.getaways.controller;

import br.com.wellwork.gs.domain.Tarefa;
import br.com.wellwork.gs.getaways.dto.request.CreateTarefaRequestDTO;
import br.com.wellwork.gs.getaways.dto.request.DeleteTarefaRequestDTO;
import br.com.wellwork.gs.getaways.dto.request.UpdateTarefaRequestDTO;
import br.com.wellwork.gs.getaways.dto.response.TarefaResponseDTO;
import br.com.wellwork.gs.service.TarefaService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/tarefa")
@RequiredArgsConstructor
public class TarefaController {

    private final TarefaService tarefaService;

    @GetMapping
    public ResponseEntity<?> getTarefas(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam Sort.Direction direction,
            @RequestParam(defaultValue = "10") int quantidadeListada
    ) {
        Page<Tarefa> tarefas = tarefaService.listarTarefa(page, quantidadeListada, direction);
        Page<TarefaResponseDTO> list = tarefas.map(TarefaResponseDTO::fromTarefa);

        if (tarefas.getTotalElements() == 0) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(list);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getTarefaById(@PathVariable int id) {
        TarefaResponseDTO tarefa = tarefaService.buscarPorId(id);
        return ResponseEntity.ok(tarefa);
    }

    @PostMapping
    public ResponseEntity<?> createTarefa(@RequestBody CreateTarefaRequestDTO body) {
        TarefaResponseDTO tarefa = tarefaService.criarTarefa(body);
        return ResponseEntity.status(HttpStatus.CREATED).body(tarefa);
    }

    @PatchMapping
    public ResponseEntity<?> patchTarefa(@RequestBody UpdateTarefaRequestDTO body) {
        TarefaResponseDTO tarefa = tarefaService.alterarTarefa(
                body.id(),
                body.titulo(),
                body.descricao(),
                body.dataHora(),
                body.status(),
                body.usuarioIds()
        );
        return ResponseEntity.ok(tarefa);
    }

    @DeleteMapping
    public ResponseEntity<?> deleteTarefa(@RequestBody DeleteTarefaRequestDTO body) {
        TarefaResponseDTO tarefa = tarefaService.deletarTarefa(body.id());
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(tarefa);
    }
}
