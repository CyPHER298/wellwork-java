package br.com.wellwork.gs.getaways.controller;

import br.com.wellwork.gs.domain.AlertaCrise;
import br.com.wellwork.gs.getaways.dto.request.CreateAlertaCriseRequestDTO;
import br.com.wellwork.gs.getaways.dto.request.DeleteAlertaCriseRequestDTO;
import br.com.wellwork.gs.getaways.dto.request.UpdateAlertaCriseRequestDTO;
import br.com.wellwork.gs.getaways.dto.response.AlertaCriseResponseDTO;
import br.com.wellwork.gs.service.AlertaCriseService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/alerta-crise")
@RequiredArgsConstructor
public class AlertaCriseController {

    private final AlertaCriseService alertaCriseService;

    @GetMapping
    public ResponseEntity<?> getAlertas(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam Sort.Direction direction,
            @RequestParam(defaultValue = "10") int quantidadeListada
    ) {
        Page<AlertaCrise> alertas = alertaCriseService.listarAlerta(page, quantidadeListada, direction);
        Page<AlertaCriseResponseDTO> list = alertas.map(AlertaCriseResponseDTO::fromAlertaCrise);

        if (alertas.getTotalElements() == 0) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(list);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getAlertaById(@PathVariable int id) {
        AlertaCriseResponseDTO alerta = alertaCriseService.buscarPorId(id);
        return ResponseEntity.ok(alerta);
    }

    @PostMapping
    public ResponseEntity<?> createAlerta(@RequestBody CreateAlertaCriseRequestDTO body) {
        AlertaCriseResponseDTO alerta = alertaCriseService.criarAlerta(body);
        return ResponseEntity.status(HttpStatus.CREATED).body(alerta);
    }

    @PatchMapping
    public ResponseEntity<?> patchAlerta(@RequestBody UpdateAlertaCriseRequestDTO body) {
        AlertaCriseResponseDTO alerta = alertaCriseService.alterarAlerta(
                body.id(),
                body.dataHora(),
                body.status()
        );
        return ResponseEntity.ok(alerta);
    }

    @DeleteMapping
    public ResponseEntity<?> deleteAlerta(@RequestBody DeleteAlertaCriseRequestDTO body) {
        AlertaCriseResponseDTO alerta = alertaCriseService.deletarAlerta(body.id());
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(alerta);
    }
}
