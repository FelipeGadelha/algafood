package br.com.portfolio.algafood.api.v1.dto.response;

import br.com.portfolio.algafood.domain.entity.OrderStatus;
import br.com.portfolio.algafood.domain.entity.OrderStatusType;

import java.time.OffsetDateTime;

public class OrderStatusRs {

    private final OrderStatusType status;
    private final OffsetDateTime moment;

    public OrderStatusRs(OrderStatus orderStatus) {
        this.status = orderStatus.getStatus();
        this.moment = orderStatus.getMoment();
    }

    public OffsetDateTime getMoment() { return moment; }
    public OrderStatusType getStatus() { return status; }
}
