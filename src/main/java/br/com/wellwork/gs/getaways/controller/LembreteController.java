package br.com.wellwork.gs.getaways.controller;

import br.com.wellwork.gs.domain.Lembrete;
import br.com.wellwork.gs.getaways.dto.request.CreateLembreteRequestDTO;
import br.com.wellwork.gs.getaways.dto.request.DeleteLembreteRequestDTO;
import br.com.wellwork.gs.getaways.dto.request.UpdateLembreteRequestDTO;
import br.com.wellwork.gs.getaways.dto.response.LembreteResponseDTO;
import br.com.wellwork.gs.service.LembreteService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/lembrete")
@RequiredArgsConstructor
public class LembreteController {

    private final LembreteService lembreteService;

    @GetMapping
    public ResponseEntity<?> getLembretes(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam Sort.Direction direction,
            @RequestParam(defaultValue = "10") int quantidadeListada
    ) {
        Page<Lembrete> lembretes = lembreteService.listarLembrete(page, quantidadeListada, direction);
        Page<LembreteResponseDTO> list = lembretes.map(LembreteResponseDTO::fromLembrete);

        if (lembretes.getTotalElements() == 0) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(list);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getLembreteById(@PathVariable int id) {
        LembreteResponseDTO lembrete = lembreteService.buscarPorId(id);
        return ResponseEntity.ok(lembrete);
    }

    @PostMapping
    public ResponseEntity<?> createLembrete(@RequestBody CreateLembreteRequestDTO body) {
        LembreteResponseDTO lembrete = lembreteService.criarLembrete(body);
        return ResponseEntity.status(HttpStatus.CREATED).body(lembrete);
    }

    @PatchMapping
    public ResponseEntity<?> patchLembrete(@RequestBody UpdateLembreteRequestDTO body) {
        LembreteResponseDTO lembrete = lembreteService.alterarLembrete(
                body.id(),
                body.tipo(),
                body.sequencia(),
                body.ativo()
        );
        return ResponseEntity.ok(lembrete);
    }

    @DeleteMapping
    public ResponseEntity<?> deleteLembrete(@RequestBody DeleteLembreteRequestDTO body) {
        LembreteResponseDTO lembrete = lembreteService.deletarLembrete(body.id());
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(lembrete);
    }
}
