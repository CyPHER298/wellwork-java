package br.com.wellwork.gs.getaways.controller;

import br.com.wellwork.gs.domain.Usuario;
import br.com.wellwork.gs.getaways.dto.request.LoginRequestDTO;
import br.com.wellwork.gs.getaways.dto.request.RegisterRequestDTO;
import br.com.wellwork.gs.getaways.dto.response.LoginResponseDTO;
import br.com.wellwork.gs.getaways.dto.response.RegisterResponseDTO;
import br.com.wellwork.gs.getaways.repository.UsuarioRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    @GetMapping
    public ResponseEntity<String> getToken(Authentication authentication) {
        if (authentication == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Usuario no encontrado");
        }
        return ResponseEntity.ok(authentication.getName());
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody @Valid LoginRequestDTO body) {
        UsernamePasswordAuthenticationToken userPass = new UsernamePasswordAuthenticationToken(body.email(), body.password());
        Authentication authentication = authenticationManager.authenticate(userPass);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token =authentication.getName();
        return ResponseEntity.ok(LoginResponseDTO.ofToken(token));
    }

    @PostMapping("/register")
    public ResponseEntity<RegisterResponseDTO> register(@RequestBody @Valid RegisterRequestDTO body) {

        if (usuarioRepository.existsByEmail(body.email())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }

        Usuario usuario = Usuario.builder()
                .nome(body.nome())
                .email(body.email())
                .senha(passwordEncoder.encode(body.senha()))
                .cargo(body.cargo())
                .build();

        usuarioRepository.save(usuario);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(RegisterResponseDTO.of(usuario.getNome(), usuario.getEmail()));
    }
}
