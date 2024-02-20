package br.com.portfolio.algafood.api.v1.dto.request;

import br.com.portfolio.algafood.domain.model.Order;
import br.com.portfolio.algafood.domain.model.PaymentMethod;
import br.com.portfolio.algafood.domain.model.Restaurant;
import br.com.portfolio.algafood.domain.model.User;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.util.List;

public record OrderRq (
    @NotNull Long restaurantId,
    @NotNull Long paymentMethodId,
    @Valid @NotNull AddressRq address,
    @Valid @Size(min = 1) @NotNull List<OrderItemRq> itens
) {
    public Order convert(User user) {
        return Order.builder()
                .client(user)
                .restaurant(Restaurant.builder()
                        .id(restaurantId)
                        .build()
                ).paymentMethod(new PaymentMethod(paymentMethodId, null))
                .addressDelivery(address.convert())
                .ordersItens(itens.stream()
                        .map(OrderItemRq::convert)
                        .toList()
                ).build();
    }
}
