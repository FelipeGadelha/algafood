package br.com.portfolio.algafood.api.v1.dto.request;

import br.com.portfolio.algafood.domain.model.Product;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;

public record ProductRq(
    @NotBlank String name,
    @NotBlank String description,
    @NotNull @PositiveOrZero BigDecimal price,
    @NotNull Boolean active
) {
    public Product convert() {
        return Product.builder()
                .name(name)
                .description(description)
                .price(price)
                .active(active)
                .build();
    }

}
