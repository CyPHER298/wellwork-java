package br.com.wellwork.gs.service;

import br.com.wellwork.gs.domain.AlertaCrise;
import br.com.wellwork.gs.domain.Gestor;
import br.com.wellwork.gs.domain.Usuario;
import br.com.wellwork.gs.getaways.dto.request.CreateAlertaCriseRequestDTO;
import br.com.wellwork.gs.getaways.dto.response.AlertaCriseResponseDTO;
import br.com.wellwork.gs.getaways.repository.AlertaCriseRepository;
import br.com.wellwork.gs.getaways.repository.GestorRepository;
import br.com.wellwork.gs.getaways.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AlertaCriseService {

    private final AlertaCriseRepository alertaCriseRepository;
    private final UsuarioRepository usuarioRepository;
    private final GestorRepository gestorRepository;

    public Page<AlertaCrise> listarAlerta(int page, int quantidadeListada, Sort.Direction direction) {
        Pageable pageable = PageRequest.of(
                page, quantidadeListada, Sort.by(direction, "dataHora")
        );
        return alertaCriseRepository.findAll(pageable);
    }

    @Transactional
    public AlertaCriseResponseDTO criarAlerta(CreateAlertaCriseRequestDTO body) {
        Usuario usuario = usuarioRepository.findById(body.usuarioId())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        Gestor gestor = gestorRepository.findById(body.gestorId())
                .orElseThrow(() -> new RuntimeException("Gestor não encontrado"));

        AlertaCrise alerta = new AlertaCrise();
        alerta.setUsuario(usuario);
        alerta.setGestor(gestor);
        alerta.setDataHora(body.dataHora() != null ? body.dataHora() : LocalDateTime.now());
        alerta.setStatus(body.status());

        alertaCriseRepository.save(alerta);
        return AlertaCriseResponseDTO.fromAlertaCrise(alerta);
    }

    @Transactional
    public AlertaCriseResponseDTO alterarAlerta(int id, LocalDateTime dataHora, br.com.wellwork.gs.domain.enums.EnumStatusAlerta status) {
        AlertaCrise alerta = alertaCriseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Alerta não encontrado"));

        if (dataHora != null) alerta.setDataHora(dataHora);
        if (status != null) alerta.setStatus(status);

        alertaCriseRepository.save(alerta);
        return AlertaCriseResponseDTO.fromAlertaCrise(alerta);
    }

    @Transactional
    public AlertaCriseResponseDTO deletarAlerta(int id) {
        AlertaCrise alerta = alertaCriseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Alerta não encontrado"));

        alertaCriseRepository.delete(alerta);
        return AlertaCriseResponseDTO.fromAlertaCrise(alerta);
    }

    public AlertaCriseResponseDTO buscarPorId(int id) {
        AlertaCrise alerta = alertaCriseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Alerta não encontrado"));
        return AlertaCriseResponseDTO.fromAlertaCrise(alerta);
    }
}
