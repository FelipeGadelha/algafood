package br.com.portfolio.algafood.api.v1.dto.request;

import br.com.portfolio.algafood.api.validator.annotation.TaxFreight;
import br.com.portfolio.algafood.domain.model.Kitchen;
import br.com.portfolio.algafood.domain.model.Restaurant;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;

@Validated
public record RestaurantRq(
    @NotBlank
    String name,
    @NotNull @PositiveOrZero @TaxFreight
    BigDecimal taxFreight,
    @NotNull @Positive
    Long kitchenId,
    @Valid
    @NotNull
    AddressRq address

) {
    public Restaurant convert() {
        return Restaurant.builder()
                .name(name)
                .taxFreight(taxFreight)
                .kitchen(new Kitchen(kitchenId, null))
                .address(address.convert())
                .build();
    }
}
