package br.com.portfolio.algafood.domain.model;

import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;
import java.time.OffsetDateTime;
import java.util.Objects;

@Embeddable
public class OrderStatus implements Comparable<OrderStatus> {

    @Enumerated(EnumType.STRING)
    private OrderStatusType status;
    private OffsetDateTime moment;
    @Deprecated public OrderStatus() { }

    public OrderStatus(OrderStatusType status) {
        this.status = status;
        this.moment = OffsetDateTime.now();
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
//    public boolean readyForConfirmation() {
//        return this.getStatus() == OrderStatusType.CREATED;
//    }
//    public boolean readyForDeliver() {
//        return this.getStatus().equals(OrderStatusType.CONFIRMED) &&
//                        !this.getStatus().equals(OrderStatusType.CANCELED);
//    }
//    public boolean readyForCancellation() {
//        return this.getStatus().equals(OrderStatusType.CREATED);
//    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderStatus that = (OrderStatus) o;
        return status == that.status;
    }
    @Override public int hashCode() { return Objects.hash(status); }
    @Override public int compareTo(@NotNull OrderStatus o) { return this.status.compareTo(o.status); }
}
