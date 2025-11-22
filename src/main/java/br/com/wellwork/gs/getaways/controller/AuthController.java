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
    public String getToken(Authentication authentication) {
        return authentication.getName();
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody @Valid LoginRequestDTO body) {
        UsernamePasswordAuthenticationToken userPass = new UsernamePasswordAuthenticationToken(body.email(), body.password());
        Authentication authentication = authenticationManager.authenticate(userPass);

        return null;
    }

    @PostMapping("/register")
    public ResponseEntity<RegisterResponseDTO> register(@RequestBody @Valid RegisterRequestDTO body) {
        Usuario usuario = new Usuario();
        usuario.setSenha(passwordEncoder.encode(body.senha()));
        usuario.setEmail(body.email());
        usuario.setNome(body.nome());
        usuario.setCargo(body.cargo());

        usuarioRepository.save(usuario);

        return ResponseEntity.status(HttpStatus.CREATED).body(new RegisterResponseDTO(usuario.getNome(), usuario.getEmail()));
    }




}
