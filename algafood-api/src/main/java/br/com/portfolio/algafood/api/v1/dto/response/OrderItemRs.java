package br.com.portfolio.algafood.api.v1.dto.response;

import br.com.portfolio.algafood.api.v1.dto.View;
import br.com.portfolio.algafood.domain.entity.OrderItem;
import com.fasterxml.jackson.annotation.JsonView;

import java.math.BigDecimal;

public class OrderItemRs {

    @JsonView({View.Basic.class, View.Detail.class})
    private Long id;
    @JsonView({View.Basic.class, View.Detail.class})
    private Integer quantity;
    @JsonView(View.Detail.class)
    private BigDecimal unitPrice;
    @JsonView({View.Basic.class, View.Detail.class})
    private BigDecimal totalPrice;
    @JsonView(View.Detail.class)
    private String observation;
    @JsonView({View.Basic.class, View.Detail.class})
    private String orderCode;
    @JsonView(View.Detail.class)
    private Long productId;
    @JsonView({View.Basic.class, View.Detail.class})
    private String productName;

    public OrderItemRs(OrderItem orderItem) {
        this.id = orderItem.getId();
        this.quantity = orderItem.getQuantity();
        this.unitPrice = orderItem.getUnitPrice();
        this.totalPrice = orderItem.getTotalPrice();
        this.observation = orderItem.getObservation();
        this.orderCode = orderItem.getOrder().getCode();
        this.productId = orderItem.getProduct().getId();
        this.productName = orderItem.getProduct().getName();
    }
    public Long getId() { return id; }
    public Integer getQuantity() { return quantity; }
    public BigDecimal getUnitPrice() { return unitPrice; }
    public BigDecimal getTotalPrice() { return totalPrice; }
    public String getObservation() { return observation; }
    public String getOrderId() { return orderCode; }
    public Long getProductId() { return productId; }
    public String getProductName() { return productName; }
}
