package br.com.wellwork.gs.service;

import br.com.wellwork.gs.domain.Usuario;
import br.com.wellwork.gs.getaways.dto.request.CreateUsuarioRequestDTO;
import br.com.wellwork.gs.getaways.dto.response.UsuarioResponseDTO;
import br.com.wellwork.gs.getaways.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;

    public Page<Usuario> listarUsuario(int page, int quantidadeListada, Sort.Direction direction) {
        Pageable pageable = PageRequest.of(
                page, quantidadeListada, Sort.by(direction, "nome")
        );
        return usuarioRepository.findAll(pageable);
    }

    @Transactional
    public UsuarioResponseDTO alterarUsuario(int id, String nome) {
        Usuario usuario = usuarioRepository.findById(id).orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        usuario.setNome(nome);
        usuarioRepository.save(usuario);

        return UsuarioResponseDTO.fromUsuario(usuario);
    }

    @Transactional
    public UsuarioResponseDTO deletarUsuario(int id) {
        Usuario usuario = usuarioRepository.findById(id).orElseThrow(() ->  new RuntimeException("Usuário não encontrado"));
        usuarioRepository.delete(usuario);
        return UsuarioResponseDTO.fromUsuario(usuario);
    }

    @Transactional
    public UsuarioResponseDTO criarUsuario(CreateUsuarioRequestDTO body) {
        Usuario usuario = new Usuario();
        usuario.setEmail(body.email());
        usuario.setCargo(body.cargo());
        usuario.setNome(body.nome());
        usuario.setAcessibilidade(body.acessibilidade());
        usuario.setSenha(body.senha());
        usuarioRepository.save(usuario);
        return UsuarioResponseDTO.fromUsuario(usuario);
    }

}
