package br.com.wellwork.gs.getaways.controller;

import br.com.wellwork.gs.domain.Usuario;
import br.com.wellwork.gs.getaways.dto.request.CreateUsuarioRequestDTO;
import br.com.wellwork.gs.getaways.dto.request.DeleteUsuarioRequestDTO;
import br.com.wellwork.gs.getaways.dto.request.UpdateUsuarioRequestDTO;
import br.com.wellwork.gs.getaways.dto.response.UsuarioResponseDTO;
import br.com.wellwork.gs.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/usuario")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;

    @GetMapping
    public ResponseEntity<?> getUsuarios(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam Sort.Direction direction,
            @RequestParam(defaultValue = "10") int quantidadeListada
    ) {
        Page<Usuario> usuarios = usuarioService.listarUsuarios(page, quantidadeListada, direction);
        Page<UsuarioResponseDTO> list = usuarios.map(UsuarioResponseDTO::fromUsuario);

        if (usuarios.getTotalElements() == 0) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(list);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUsuarioById(@PathVariable int id) {
        UsuarioResponseDTO usuario = usuarioService.buscarPorId(id);
        return ResponseEntity.ok(usuario);
    }

    @PostMapping
    public ResponseEntity<?> createUsuario(@RequestBody CreateUsuarioRequestDTO body) {
        UsuarioResponseDTO usuario = usuarioService.criarUsuario(body);
        return ResponseEntity.status(HttpStatus.CREATED).body(usuario);
    }

    @PatchMapping
    public ResponseEntity<?> patchUsuario(@RequestBody UpdateUsuarioRequestDTO body) {
        UsuarioResponseDTO usuario = usuarioService.alterarUsuario(
                body.id(),
                body.nome(),
                body.email(),
                body.senha(),
                body.cargo(),
                body.acessibilidade()
        );
        return ResponseEntity.ok(usuario);
    }

    @DeleteMapping
    public ResponseEntity<?> deleteUsuario(@RequestBody DeleteUsuarioRequestDTO body) {
        UsuarioResponseDTO usuario = usuarioService.deletarUsuario(body.id());
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(usuario);
    }
}
