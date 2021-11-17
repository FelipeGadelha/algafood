package br.com.portfolio.algafood.api.v1.dto.request;

import br.com.portfolio.algafood.domain.entity.Product;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;

public class ProductRq {

    @NotBlank
    private String name;

    @NotBlank
    private String description;

    @NotNull
    @PositiveOrZero
    private BigDecimal price;

    @NotNull
    private Boolean active;

    public ProductRq(String name, String description, BigDecimal price, Boolean active) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.active = active;
    }

    public Product convert() {
        return Product.builder()
                .name(name)
                .description(description)
                .price(price)
                .active(active)
                .build();
    }

}
