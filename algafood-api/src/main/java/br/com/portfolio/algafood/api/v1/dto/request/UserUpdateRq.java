package br.com.portfolio.algafood.api.v1.dto.request;

import br.com.portfolio.algafood.api.validator.annotation.UniqueValue;
import br.com.portfolio.algafood.domain.entity.User;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class UserUpdateRq {

    @NotBlank
    private final String name;
    @NotBlank @Email
    private final String email;

    public UserUpdateRq(String name, String email) {
        this.name = name;
        this.email = email;
    }
    public User convert() {
        return User.builder()
                .name(name)
                .email(email)
                .build();
    }
}
