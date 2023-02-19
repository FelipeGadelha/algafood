package br.com.portfolio.algafood.api.v1.dto.request;

import br.com.portfolio.algafood.domain.entity.OrderItem;
import br.com.portfolio.algafood.domain.entity.Product;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

public record OrderItemRq(
    @NotNull Long productId,
    @NotNull @PositiveOrZero Integer quantity,
    String observation
) {
    public OrderItem convert() {
        return OrderItem.builder()
                .product(Product.builder()
                        .id(productId)
                        .build()
                ).quantity(quantity)
                .observation(observation)
                .build();
    }
}
