package br.com.portfolio.algafood.domain.event;

import br.com.portfolio.algafood.domain.model.Order;

public class ConfirmedOrderEvent {
    private Order order;

    public ConfirmedOrderEvent(Order order) { this.order = order; }

    public Order getOrder() { return order; }
}
