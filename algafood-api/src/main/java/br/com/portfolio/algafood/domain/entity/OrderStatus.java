package br.com.portfolio.algafood.domain.entity;

import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.OffsetDateTime;

@Embeddable
public class OrderStatus {

    @Enumerated(EnumType.STRING)
    private OrderStatusType status;

    private OffsetDateTime moment;

    @Deprecated public OrderStatus() { }

    public OrderStatus(OrderStatusType status, OffsetDateTime moment) {
        this.status = status;
        this.moment = moment;
    }

    public OrderStatusType getStatus() { return status; }
    public OffsetDateTime getMoment() { return moment; }

    @Override
    public String toString() {
        return "OrderStatus{" +
                "status=" + status +
                ", moment=" + moment +
                '}';
    }
}
