package br.com.wellwork.gs.service;

import br.com.wellwork.gs.domain.Meta;
import br.com.wellwork.gs.domain.Usuario;
import br.com.wellwork.gs.getaways.dto.request.CreateMetaRequestDTO;
import br.com.wellwork.gs.getaways.dto.response.MetaResponseDTO;
import br.com.wellwork.gs.getaways.repository.MetaRepository;
import br.com.wellwork.gs.getaways.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MetaService {

    private final MetaRepository metaRepository;
    private final UsuarioRepository usuarioRepository;

    public Page<Meta> listarMeta(int page, int quantidadeListada, Sort.Direction direction) {
        Pageable pageable = PageRequest.of(
                page, quantidadeListada, Sort.by(direction, "titulo")
        );
        return metaRepository.findAll(pageable);
    }

    @Transactional
    public MetaResponseDTO criarMeta(CreateMetaRequestDTO body) {
        Usuario usuario = null;
        if (body.usuarioId() != null) {
            usuario = usuarioRepository.findById(body.usuarioId())
                    .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        }

        Meta meta = new Meta();
        meta.setTitulo(body.titulo());
        meta.setDescricao(body.descricao());
        meta.setUsuario(usuario);

        metaRepository.save(meta);
        return MetaResponseDTO.fromMeta(meta);
    }

    @Transactional
    public MetaResponseDTO alterarMeta(int id, String titulo, String descricao) {
        Meta meta = metaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Meta não encontrada"));

        if (titulo != null) meta.setTitulo(titulo);
        if (descricao != null) meta.setDescricao(descricao);

        metaRepository.save(meta);
        return MetaResponseDTO.fromMeta(meta);
    }

    @Transactional
    public MetaResponseDTO deletarMeta(int id) {
        Meta meta = metaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Meta não encontrada"));
        metaRepository.delete(meta);
        return MetaResponseDTO.fromMeta(meta);
    }

    public MetaResponseDTO buscarPorId(int id) {
        Meta meta = metaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Meta não encontrada"));
        return MetaResponseDTO.fromMeta(meta);
    }
}