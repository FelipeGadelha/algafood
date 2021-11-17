package br.com.portfolio.algafood.api.v1.dto.request;

import br.com.portfolio.algafood.core.validation.TaxFreight;
import br.com.portfolio.algafood.domain.entity.Kitchen;
import br.com.portfolio.algafood.domain.entity.Restaurant;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;

@Validated
public class RestaurantRq {

    @NotBlank
    private final String name;
    @NotNull @PositiveOrZero @TaxFreight
    private final BigDecimal taxFreight;
    @NotNull @Positive
    private final Long kitchenId;
    @Valid
    @NotNull
    private AddressRq address;

    public RestaurantRq(String name, BigDecimal taxFreight, Long kitchenId, AddressRq address) {
        this.name = name;
        this.taxFreight = taxFreight;
        this.kitchenId = kitchenId;
        this.address = address;
    }
    public Restaurant convert() {
        return Restaurant.builder()
                .name(name)
                .taxFreight(taxFreight)
                .kitchen(new Kitchen(kitchenId, null))
                .address(address.convert())
                .build();
    }
}
