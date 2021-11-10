package br.com.portfolio.algafood.api.v1.dto.request;

import br.com.portfolio.algafood.domain.entity.PaymentMethod;
import com.fasterxml.jackson.annotation.JsonCreator;

import javax.validation.constraints.NotBlank;

public class PaymentMethodRq {

    @NotBlank
    private final String description;
    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public PaymentMethodRq(String description) { this.description = description; }
    public PaymentMethod convert() { return new PaymentMethod(null, description); }
}
