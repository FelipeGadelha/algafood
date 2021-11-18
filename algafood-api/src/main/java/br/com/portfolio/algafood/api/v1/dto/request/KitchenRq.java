package br.com.portfolio.algafood.api.v1.dto.request;

import br.com.portfolio.algafood.domain.entity.Kitchen;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;

public record KitchenRq(
        @JsonProperty("name") @NotBlank String name
) {
    public Kitchen convert() {
        return new Kitchen(null, name);
    }
}
