package br.com.wellwork.gs.service;

import br.com.wellwork.gs.domain.Timer;
import br.com.wellwork.gs.domain.Usuario;
import br.com.wellwork.gs.domain.enums.EnumStatusTimer;
import br.com.wellwork.gs.getaways.dto.request.CreateTimerRequestDTO;
import br.com.wellwork.gs.getaways.dto.response.TimerResponseDTO;
import br.com.wellwork.gs.getaways.repository.TimerRepository;
import br.com.wellwork.gs.getaways.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.time.Duration;

@Service
@RequiredArgsConstructor
public class TimerService {

    private final TimerRepository timerRepository;
    private final UsuarioRepository usuarioRepository;

    public Page<Timer> listarTimer(int page, int quantidadeListada, Sort.Direction direction) {
        Pageable pageable = PageRequest.of(
                page, quantidadeListada, Sort.by(direction, "inicio")
        );
        return timerRepository.findAll(pageable);
    }

    @Transactional
    public TimerResponseDTO criarTimer(CreateTimerRequestDTO body) {
        Usuario usuario = usuarioRepository.findById(body.usuarioId())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        Timer timer = new Timer();
        timer.setTipo(body.tipo());
        timer.setDuracao(body.duracaoSegundos() != null ? Duration.ofSeconds(body.duracaoSegundos()) : null);
        timer.setInicio(body.inicio());
        timer.setFim(body.fim());
        timer.setStatus(body.status());
        timer.setUsuario(usuario);

        timerRepository.save(timer);
        return TimerResponseDTO.fromTimer(timer);
    }

    @Transactional
    public TimerResponseDTO alterarTimer(
            int id,
            String tipo,
            Long duracaoSegundos,
            java.time.LocalDateTime inicio,
            java.time.LocalDateTime fim,
            EnumStatusTimer status
    ) {
        Timer timer = timerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Timer não encontrado"));

        if (tipo != null) timer.setTipo(tipo);
        if (duracaoSegundos != null) timer.setDuracao(Duration.ofSeconds(duracaoSegundos));
        if (inicio != null) timer.setInicio(inicio);
        if (fim != null) timer.setFim(fim);
        if (status != null) timer.setStatus(status);

        timerRepository.save(timer);
        return TimerResponseDTO.fromTimer(timer);
    }

    @Transactional
    public TimerResponseDTO deletarTimer(int id) {
        Timer timer = timerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Timer não encontrado"));

        timerRepository.delete(timer);
        return TimerResponseDTO.fromTimer(timer);
    }

    public TimerResponseDTO buscarPorId(int id) {
        Timer timer = timerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Timer não encontrado"));

        return TimerResponseDTO.fromTimer(timer);
    }
}
