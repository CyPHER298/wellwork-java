package br.com.wellwork.gs.service;

import br.com.wellwork.gs.domain.Lembrete;
import br.com.wellwork.gs.getaways.dto.request.CreateLembreteRequestDTO;
import br.com.wellwork.gs.getaways.dto.response.LembreteResponseDTO;
import br.com.wellwork.gs.getaways.repository.LembreteRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LembreteService {

    private final LembreteRepository lembreteRepository;

    public Page<Lembrete> listarLembrete(int page, int quantidadeListada, Sort.Direction direction) {
        Pageable pageable = PageRequest.of(
                page, quantidadeListada, Sort.by(direction, "tipo")
        );
        return lembreteRepository.findAll(pageable);
    }

    @Transactional
    public LembreteResponseDTO criarLembrete(CreateLembreteRequestDTO body) {
        Lembrete lembrete = new Lembrete();
        lembrete.setTipo(body.tipo());
        lembrete.setSequencia(body.sequencia());
        lembrete.setAtivo(body.ativo());

        lembreteRepository.save(lembrete);
        return LembreteResponseDTO.fromLembrete(lembrete);
    }

    @Transactional
    public LembreteResponseDTO alterarLembrete(int id, String tipo, String sequencia, br.com.wellwork.gs.domain.enums.EnumAtivoLembrete ativo) {
        Lembrete lembrete = lembreteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Lembrete não encontrado"));

        if (tipo != null) lembrete.setTipo(tipo);
        if (sequencia != null) lembrete.setSequencia(sequencia);
        if (ativo != null) lembrete.setAtivo(ativo);

        lembreteRepository.save(lembrete);
        return LembreteResponseDTO.fromLembrete(lembrete);
    }

    @Transactional
    public LembreteResponseDTO deletarLembrete(int id) {
        Lembrete lembrete = lembreteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Lembrete não encontrado"));
        lembreteRepository.delete(lembrete);
        return LembreteResponseDTO.fromLembrete(lembrete);
    }

    public LembreteResponseDTO buscarPorId(int id) {
        Lembrete lembrete = lembreteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Lembrete não encontrado"));
        return LembreteResponseDTO.fromLembrete(lembrete);
    }
}
