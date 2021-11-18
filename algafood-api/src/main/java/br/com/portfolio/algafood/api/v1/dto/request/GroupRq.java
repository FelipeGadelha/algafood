package br.com.portfolio.algafood.api.v1.dto.request;

import br.com.portfolio.algafood.domain.entity.Group;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;

public record GroupRq(
        @JsonProperty("name") @NotBlank String name
) {
    public Group convert() {
        return Group.builder()
                .name(name)
                .build();
    }
}
