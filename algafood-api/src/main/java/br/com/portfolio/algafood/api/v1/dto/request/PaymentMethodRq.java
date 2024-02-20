package br.com.portfolio.algafood.api.v1.dto.request;

import br.com.portfolio.algafood.domain.model.PaymentMethod;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotBlank;

public record PaymentMethodRq(
        @JsonProperty("description") @NotBlank String description
) {
    public PaymentMethod convert() {
        return new PaymentMethod(null, description);
    }
}
