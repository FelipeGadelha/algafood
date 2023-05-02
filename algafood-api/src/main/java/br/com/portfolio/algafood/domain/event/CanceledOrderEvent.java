package br.com.portfolio.algafood.domain.event;

import br.com.portfolio.algafood.domain.model.Order;

public class CanceledOrderEvent {
    private Order order;

    public CanceledOrderEvent(Order order) { this.order = order; }

    public Order getOrder() { return order; }
}
