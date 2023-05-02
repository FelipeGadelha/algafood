package br.com.portfolio.algafood.api.v1.dto.request;

import br.com.portfolio.algafood.domain.model.City;
import br.com.portfolio.algafood.domain.model.State;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public record CityRq(
        @NotBlank String name,
        @NotNull Long stateId
) {
    public City convert() {
        return City.builder()
                .name(name)
                .state(new State(stateId, null))
                .build();
    }
}
