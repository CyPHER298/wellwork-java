package br.com.wellwork.gs.getaways.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record LoginRequestDTO(
        @NotNull
        String email,
        @NotNull
        String password
) {
}
