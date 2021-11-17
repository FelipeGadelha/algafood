package br.com.portfolio.algafood.api.v1.dto.request;

import br.com.portfolio.algafood.domain.entity.City;
import br.com.portfolio.algafood.domain.entity.State;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class CityRq {

    @NotBlank
    private final String name;
    @NotNull
    private final Long stateId;

    public CityRq(String name, Long stateId) {
        this.name = name;
        this.stateId = stateId;
    }

    public City convert() {
        return City.builder()
                .name(name)
                .state(new State(stateId, null))
                .build();
    }
}
