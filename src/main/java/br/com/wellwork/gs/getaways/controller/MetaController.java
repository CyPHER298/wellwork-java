package br.com.wellwork.gs.getaways.controller;

import br.com.wellwork.gs.domain.Meta;
import br.com.wellwork.gs.getaways.dto.request.CreateMetaRequestDTO;
import br.com.wellwork.gs.getaways.dto.request.DeleteMetaRequestDTO;
import br.com.wellwork.gs.getaways.dto.request.UpdateMetaRequestDTO;
import br.com.wellwork.gs.getaways.dto.response.MetaResponseDTO;
import br.com.wellwork.gs.service.MetaService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/meta")
@RequiredArgsConstructor
public class MetaController {

    private final MetaService metaService;

    @GetMapping
    public ResponseEntity<?> getMetas(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam Sort.Direction direction,
            @RequestParam(defaultValue = "10") int quantidadeListada
    ) {
        Page<Meta> metas = metaService.listarMeta(page, quantidadeListada, direction);
        Page<MetaResponseDTO> list = metas.map(MetaResponseDTO::fromMeta);

        if (metas.getTotalElements() == 0) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(list);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getMetaById(@PathVariable int id) {
        MetaResponseDTO meta = metaService.buscarPorId(id);
        return ResponseEntity.ok(meta);
    }

    @PostMapping
    public ResponseEntity<?> createMeta(@RequestBody CreateMetaRequestDTO body) {
        MetaResponseDTO meta = metaService.criarMeta(body);
        return ResponseEntity.status(HttpStatus.CREATED).body(meta);
    }

    @PatchMapping
    public ResponseEntity<?> patchMeta(@RequestBody UpdateMetaRequestDTO body) {
        MetaResponseDTO meta = metaService.alterarMeta(body.id(), body.titulo(), body.descricao());
        return ResponseEntity.ok(meta);
    }

    @DeleteMapping
    public ResponseEntity<?> deleteMeta(@RequestBody DeleteMetaRequestDTO body) {
        MetaResponseDTO meta = metaService.deletarMeta(body.id());
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(meta);
    }
}
