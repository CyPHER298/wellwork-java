package br.com.wellwork.gs.getaways.dto.response;

public record LoginResponseDTO(String token) {

    public static LoginResponseDTO ofToken(String token) {
        return new LoginResponseDTO(token);
    }
}
