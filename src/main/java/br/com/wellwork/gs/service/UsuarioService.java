package br.com.wellwork.gs.service;

import br.com.wellwork.gs.domain.Usuario;
import br.com.wellwork.gs.domain.enums.EnumCargoUsuario;
import br.com.wellwork.gs.getaways.dto.request.CreateUsuarioRequestDTO;
import br.com.wellwork.gs.getaways.dto.response.UsuarioResponseDTO;
import br.com.wellwork.gs.getaways.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public Page<Usuario> listarUsuarios(int page, int quantidadeListada, Sort.Direction direction) {
        Pageable pageable = PageRequest.of(
                page, quantidadeListada, Sort.by(direction, "nome")
        );
        return usuarioRepository.findAll(pageable);
    }

    public UsuarioResponseDTO buscarPorId(int id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        return UsuarioResponseDTO.fromUsuario(usuario);
    }

    @Transactional
    public UsuarioResponseDTO criarUsuario(CreateUsuarioRequestDTO body) {
        Usuario usuario = new Usuario();
        usuario.setNome(body.nome());
        usuario.setEmail(body.email());
        usuario.setSenha(body.senha());
        usuario.setCargo(body.cargo());
        usuario.setAcessibilidade(body.acessibilidade());

        usuarioRepository.save(usuario);
        return UsuarioResponseDTO.fromUsuario(usuario);
    }

    @Transactional
    public UsuarioResponseDTO alterarUsuario(int id, String nome, String email, String senha, EnumCargoUsuario cargo, String acessibilidade) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        if (nome != null) usuario.setNome(nome);
        if (email != null) usuario.setEmail(email);
        if (senha != null) usuario.setSenha(senha);
        if (cargo != null) usuario.setCargo(cargo);
        if (acessibilidade != null) usuario.setAcessibilidade(acessibilidade);

        usuarioRepository.save(usuario);
        return UsuarioResponseDTO.fromUsuario(usuario);
    }

    @Transactional
    public UsuarioResponseDTO deletarUsuario(int id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        usuarioRepository.delete(usuario);
        return UsuarioResponseDTO.fromUsuario(usuario);
    }
}
