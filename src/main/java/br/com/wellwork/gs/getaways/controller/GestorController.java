package br.com.wellwork.gs.getaways.controller;

import br.com.wellwork.gs.domain.Gestor;
import br.com.wellwork.gs.getaways.dto.request.CreateGestorRequestDTO;
import br.com.wellwork.gs.getaways.dto.request.DeleteGestorRequestDTO;
import br.com.wellwork.gs.getaways.dto.request.UpdateGestorRequestDTO;
import br.com.wellwork.gs.getaways.dto.response.GestorResponseDTO;
import br.com.wellwork.gs.service.GestorService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/gestor")
@RequiredArgsConstructor
public class GestorController {

    private final GestorService gestorService;

    @GetMapping
    public ResponseEntity<?> getGestores(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam Sort.Direction direction,
            @RequestParam(defaultValue = "10") int quantidadeListada
    ) {
        Page<Gestor> gestores = gestorService.listarGestor(page, quantidadeListada, direction);
        Page<GestorResponseDTO> list = gestores.map(GestorResponseDTO::fromGestor);

        if (gestores.getTotalElements() == 0) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(list);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getGestorById(@PathVariable int id) {
        GestorResponseDTO gestor = gestorService.buscarPorId(id);
        return ResponseEntity.ok(gestor);
    }

    @PostMapping
    public ResponseEntity<?> createGestor(@RequestBody CreateGestorRequestDTO body) {
        GestorResponseDTO gestor = gestorService.criarGestor(body);
        return ResponseEntity.status(HttpStatus.CREATED).body(gestor);
    }

    @PatchMapping
    public ResponseEntity<?> patchGestor(@RequestBody UpdateGestorRequestDTO body) {
        GestorResponseDTO gestor = gestorService.alterarGestor(
                body.id(),
                body.nome(),
                body.email(),
                body.cargo(),
                body.departamento()
        );
        return ResponseEntity.ok(gestor);
    }

    @DeleteMapping
    public ResponseEntity<?> deleteGestor(@RequestBody DeleteGestorRequestDTO body) {
        GestorResponseDTO gestor = gestorService.deletarGestor(body.id());
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(gestor);
    }
}
