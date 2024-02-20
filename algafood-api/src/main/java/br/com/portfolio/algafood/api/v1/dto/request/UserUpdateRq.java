package br.com.portfolio.algafood.api.v1.dto.request;

import br.com.portfolio.algafood.domain.model.User;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UserUpdateRq(
        @NotBlank String name,
        @NotBlank @Email String email
) {
    public User convert() {
        return User.builder()
                .name(name)
                .email(email)
                .build();
    }
}
