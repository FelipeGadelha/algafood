package br.com.portfolio.algafood.api.v1.dto.request;

import br.com.portfolio.algafood.domain.entity.PaymentMethod;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;

public record PaymentMethodRq(
        @JsonProperty("description") @NotBlank String description
) {
    public PaymentMethod convert() {
        return new PaymentMethod(null, description);
    }
}
