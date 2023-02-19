package br.com.portfolio.algafood.api.v1.dto.request;

import br.com.portfolio.algafood.domain.entity.Permission;

import javax.validation.constraints.NotBlank;

public record PermissionRq(
        @NotBlank String name,
        @NotBlank String description
) {
    public Permission convert() {
        return Permission.builder()
                .name(name)
                .description(description)
                .build();
    }
}
