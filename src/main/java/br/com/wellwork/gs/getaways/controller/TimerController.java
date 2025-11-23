package br.com.wellwork.gs.getaways.controller;

import br.com.wellwork.gs.domain.Timer;
import br.com.wellwork.gs.getaways.dto.request.CreateTimerRequestDTO;
import br.com.wellwork.gs.getaways.dto.request.DeleteTimerRequestDTO;
import br.com.wellwork.gs.getaways.dto.request.UpdateTimerRequestDTO;
import br.com.wellwork.gs.getaways.dto.response.TimerResponseDTO;
import br.com.wellwork.gs.service.TimerService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/timer")
@RequiredArgsConstructor
public class TimerController {

    private final TimerService timerService;

    @GetMapping
    public ResponseEntity<?> getTimers(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam Sort.Direction direction,
            @RequestParam(defaultValue = "10") int quantidadeListada
    ) {
        Page<Timer> timers = timerService.listarTimer(page, quantidadeListada, direction);
        Page<TimerResponseDTO> list = timers.map(TimerResponseDTO::fromTimer);

        if (timers.getTotalElements() == 0) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(list);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getTimerById(@PathVariable int id) {
        TimerResponseDTO timer = timerService.buscarPorId(id);
        return ResponseEntity.ok(timer);
    }

    @PostMapping
    public ResponseEntity<?> createTimer(@RequestBody CreateTimerRequestDTO body) {
        TimerResponseDTO timer = timerService.criarTimer(body);
        return ResponseEntity.status(HttpStatus.CREATED).body(timer);
    }

    @PatchMapping
    public ResponseEntity<?> patchTimer(@RequestBody UpdateTimerRequestDTO body) {
        TimerResponseDTO timer = timerService.alterarTimer(
                body.id(),
                body.tipo(),
                body.duracaoSegundos(),
                body.inicio(),
                body.fim(),
                body.status()
        );
        return ResponseEntity.ok(timer);
    }

    @DeleteMapping
    public ResponseEntity<?> deleteTimer(@RequestBody DeleteTimerRequestDTO body) {
        TimerResponseDTO timer = timerService.deletarTimer(body.id());
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(timer);
    }
}
