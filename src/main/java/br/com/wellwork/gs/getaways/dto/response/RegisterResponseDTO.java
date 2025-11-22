package br.com.wellwork.gs.getaways.dto.response;

public record RegisterResponseDTO(String name, String email) {

    public static RegisterResponseDTO of(String name, String email) {
        return new RegisterResponseDTO(name, email);
    }
}
