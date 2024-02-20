package br.com.portfolio.algafood.api.v1.dto.request;

import br.com.portfolio.algafood.domain.model.OrderItem;
import br.com.portfolio.algafood.domain.model.Product;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

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
