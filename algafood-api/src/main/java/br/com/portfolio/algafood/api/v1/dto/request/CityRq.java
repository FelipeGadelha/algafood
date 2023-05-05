package br.com.portfolio.algafood.api.v1.dto.request;

import br.com.portfolio.algafood.domain.model.City;
import br.com.portfolio.algafood.domain.model.State;
import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public record CityRq(
    @Schema(description = "Nome da cidade", example = "Campos do Jordão")
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
