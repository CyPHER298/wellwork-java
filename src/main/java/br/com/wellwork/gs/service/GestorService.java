package br.com.wellwork.gs.service;

import br.com.wellwork.gs.domain.Gestor;
import br.com.wellwork.gs.getaways.dto.request.CreateGestorRequestDTO;
import br.com.wellwork.gs.getaways.dto.response.GestorResponseDTO;
import br.com.wellwork.gs.getaways.repository.GestorRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GestorService {

    private final GestorRepository gestorRepository;

    public Page<Gestor> listarGestor(int page, int quantidadeListada, Sort.Direction direction) {
        Pageable pageable = PageRequest.of(
                page, quantidadeListada, Sort.by(direction, "nome")
        );
        return gestorRepository.findAll(pageable);
    }

    @Transactional
    public GestorResponseDTO criarGestor(CreateGestorRequestDTO body) {
        Gestor gestor = new Gestor();
        gestor.setNome(body.nome());
        gestor.setEmail(body.email());
        gestor.setSenha(body.senha());
        gestor.setCargo(body.cargo());
        gestor.setDepartamento(body.departamento());

        gestorRepository.save(gestor);
        return GestorResponseDTO.fromGestor(gestor);
    }

    @Transactional
    public GestorResponseDTO alterarGestor(
            int id,
            String nome,
            String email,
            String cargo,
            String departamento
    ) {
        Gestor gestor = gestorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Gestor não encontrado"));

        if (nome != null) gestor.setNome(nome);
        if (email != null) gestor.setEmail(email);
        if (cargo != null) gestor.setCargo(cargo);
        if (departamento != null) gestor.setDepartamento(departamento);

        gestorRepository.save(gestor);
        return GestorResponseDTO.fromGestor(gestor);
    }

    @Transactional
    public GestorResponseDTO deletarGestor(int id) {
        Gestor gestor = gestorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Gestor não encontrado"));

        gestorRepository.delete(gestor);
        return GestorResponseDTO.fromGestor(gestor);
    }

    public GestorResponseDTO buscarPorId(int id) {
        Gestor gestor = gestorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Gestor não encontrado"));
        return GestorResponseDTO.fromGestor(gestor);
    }
}
