package br.com.wellwork.gs.service;

import org.springframework.stereotype.Service;

@Service
public class ConcatIdUsuarioService {
    public String execute(String id) {
        return "Usuario ".concat(id);
    }
}
