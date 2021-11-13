package br.com.portfolio.algafood.api.v1.dto.request;

import br.com.portfolio.algafood.api.validator.annotation.UniqueValue;
import br.com.portfolio.algafood.domain.entity.User;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class UserRq {

    @NotBlank
    private final String name;
    @NotBlank @Email
//    @UniqueValue(domainClass = User.class,
//            fieldName = "email",
//            message = "Não é possível realizar um cadastro com este email.")
    private final String email;
    @NotBlank
    private final String password;

    public UserRq(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }
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
