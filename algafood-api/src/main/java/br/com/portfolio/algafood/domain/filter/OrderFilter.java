package br.com.portfolio.algafood.domain.filter;

import br.com.portfolio.algafood.domain.entity.OrderStatusType;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.OffsetDateTime;

public class OrderFilter{
    private Long clientId;
    private Long restaurantId;
    private OrderStatusType status;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) private OffsetDateTime creationDate;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) private OffsetDateTime finishDate;

    public OrderFilter(Long clientId, Long restaurantId, OffsetDateTime creationDate, OffsetDateTime finishDate, OrderStatusType status) {
        this.clientId = clientId;
        this.restaurantId = restaurantId;
        this.creationDate = creationDate;
        this.finishDate = finishDate;
        this.status = status;
    }
    public Long getClientId() { return clientId; }
    public Long getRestaurantId() { return restaurantId; }
    public OrderStatusType getStatus() { return status; }
    public OffsetDateTime getCreationDate() { return creationDate; }
    public OffsetDateTime getFinishDate() { return finishDate; }
}


