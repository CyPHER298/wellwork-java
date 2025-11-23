package br.com.wellwork.gs.getaways.controller;

import br.com.wellwork.gs.domain.Usuario;
import br.com.wellwork.gs.getaways.dto.request.CreateUsuarioRequestDTO;
import br.com.wellwork.gs.getaways.dto.request.DeleteUsuarioRequestDTO;
import br.com.wellwork.gs.getaways.dto.request.UpdateUsuarioRequestDTO;
import br.com.wellwork.gs.getaways.dto.response.UsuarioResponseDTO;
import br.com.wellwork.gs.service.ConcatIdUsuarioService;
import br.com.wellwork.gs.service.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/usuario")
@RequiredArgsConstructor
public class UsuarioController {

    private final ConcatIdUsuarioService concatIdUsuarioService;
    private final UsuarioService usuarioService;

    @GetMapping("/{id}")
    @Operation(
            description = "Retorna o conteúdo de Usuario concatenado com ID"

    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200"
                    ),

                    @ApiResponse(
                            responseCode = "400"
                    ),

                    @ApiResponse(
                            responseCode = "422"
                    )
            }
    )
    public String getUsuario(@PathVariable String id) {
        String execute = concatIdUsuarioService.execute(id);
        return execute.concat("1");
    }

    @GetMapping
    public ResponseEntity<?> getUsuario(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam Sort.Direction direction,
            @RequestParam(defaultValue = "10") int quantidadeListada)
            {
        Page<Usuario> usuarios = usuarioService.listarUsuario(page, quantidadeListada, direction);

        Page<UsuarioResponseDTO> list = usuarios.map(UsuarioResponseDTO::fromUsuario);

        if (usuarios.getTotalElements() == 0) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(list);
        }
    }

    @PatchMapping
    public ResponseEntity<?> patchUsuario(@RequestBody UpdateUsuarioRequestDTO body) {
        UsuarioResponseDTO usuario = usuarioService.alterarUsuario(body.id(), body.nome());

        return ResponseEntity.ok(usuario);
    }

    @DeleteMapping
    public ResponseEntity<?> deleteUsuario(@RequestBody DeleteUsuarioRequestDTO body) {
        UsuarioResponseDTO usuario = usuarioService.deletarUsuario(body.id());

        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(usuario);
    }

    @PostMapping
    public ResponseEntity<?> createUsuario(@RequestBody CreateUsuarioRequestDTO body) {
        UsuarioResponseDTO usuario = usuarioService.criarUsuario(body);

        return ResponseEntity.status(HttpStatus.CREATED).body(usuario);
    }


}
