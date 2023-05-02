package br.com.portfolio.algafood.api.v1.dto.request;

import br.com.portfolio.algafood.domain.model.State;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;

public record StateRq(
        @JsonProperty("name") @NotBlank String name
) {
    public State convert() {
        return new State(null, name);
    }
}
