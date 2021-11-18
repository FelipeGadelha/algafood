package br.com.portfolio.algafood.api.v1.dto.request;

import br.com.portfolio.algafood.domain.entity.User;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public record UserRq(
        @NotBlank String name,
        @NotBlank @Email String email,
        @NotBlank String password
) {
    public User convert() {
        return User.builder()
                .name(name)
                .email(email)
                .password(password)
                .build();
    }
//    private String encrypt(String password) {
//        return new BCryptPasswordEncoder().encode(password);
//    }
}
