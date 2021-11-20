package br.com.portfolio.algafood.api.v1.dto.request;

import br.com.portfolio.algafood.domain.entity.Order;
import br.com.portfolio.algafood.domain.entity.PaymentMethod;
import br.com.portfolio.algafood.domain.entity.Restaurant;
import br.com.portfolio.algafood.domain.entity.User;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.stream.Collectors;

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
                        .collect(Collectors.toList())
                ).build();
    }
}
