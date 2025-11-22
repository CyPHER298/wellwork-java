package br.com.wellwork.gs.getaways.controller;

import br.com.wellwork.gs.service.ConcatIdUsuarioService;
import br.com.wellwork.gs.service.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/usuario")
@RequiredArgsConstructor
public class UsuarioController {

    private final ConcatIdUsuarioService concatIdUsuarioService;

    @GetMapping("/{id}")
    @Operation(
            description = "returns the aluno concatenated with its id"

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
    public String getUsuario(Authentication authentication, @PathVariable String id) {
        return concatIdUsuarioService.execute(id);
    }

}
