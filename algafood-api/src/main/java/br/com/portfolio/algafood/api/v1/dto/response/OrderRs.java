package br.com.portfolio.algafood.api.v1.dto.response;

import br.com.portfolio.algafood.api.v1.dto.View;
import br.com.portfolio.algafood.domain.entity.Order;
import br.com.portfolio.algafood.domain.entity.OrderStatus;
import com.fasterxml.jackson.annotation.JsonView;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class OrderRs {

    @JsonView({View.Basic.class, View.Detail.class})
    private Long id;
    @JsonView({View.Basic.class, View.Detail.class})
    private BigDecimal subtotal;
    @JsonView({View.Basic.class, View.Detail.class})
    private BigDecimal taxFreight;
    @JsonView({View.Basic.class, View.Detail.class})
    private BigDecimal totalValue;
    @JsonView({View.Basic.class, View.Detail.class})
    private OffsetDateTime creationDate;
    @JsonView(View.Detail.class)
    private OffsetDateTime confirmationDate;
    @JsonView(View.Detail.class)
    private OffsetDateTime cancelDate;
    @JsonView(View.Detail.class)
    private OffsetDateTime deliveryDate;
    @JsonView({View.Basic.class, View.Detail.class})
    private OrderStatus status;
    @JsonView(View.Detail.class)
    private PaymentMethodRs method;
    @JsonView({View.Basic.class, View.Detail.class})
    private RestaurantRs restaurant;
    @JsonView({View.Basic.class, View.Detail.class})
    private UserRs client;
    @JsonView(View.Detail.class)
    private AddressRs addressDelivery;
    @JsonView(View.Detail.class)
    private List<OrderItemRs> ordersItens;

    public OrderRs(Order order) {
        this.id = order.getId();
        this.subtotal = order.getSubtotal();
        this.taxFreight = order.getTaxFreight();
        this.totalValue = order.getTotalValue();
        this.creationDate = order.getCreationDate();
        this.confirmationDate = order.getConfirmationDate();
        this.cancelDate = order.getCancelDate();
        this.deliveryDate = order.getDeliveryDate();
        this.status = order.getStatus();
        this.method = new PaymentMethodRs(order.getMethod());
        this.restaurant = new RestaurantRs(order.getRestaurant());
        this.client = new UserRs(order.getClient());
        this.addressDelivery = new AddressRs(order.getAddressDelivery());
        this.ordersItens = order.getOrdersItens().stream().map(OrderItemRs::new).collect(Collectors.toList());
    }
    public Long getId() { return id; }
    public BigDecimal getSubtotal() { return subtotal; }
    public BigDecimal getTaxFreight() { return taxFreight; }
    public BigDecimal getTotalValue() { return totalValue; }
    public OffsetDateTime getCreationDate() { return creationDate; }
    public OffsetDateTime getConfirmationDate() { return confirmationDate; }
    public OffsetDateTime getCancelDate() { return cancelDate; }
    public OffsetDateTime getDeliveryDate() { return deliveryDate; }
    public OrderStatus getStatus() { return status; }
    public PaymentMethodRs getMethod() { return method; }
    public RestaurantRs getRestaurant() { return restaurant; }
    public UserRs getClient() { return client; }
    public AddressRs getAddressDelivery() { return addressDelivery; }
    public List<OrderItemRs> getOrdersItens() { return ordersItens; }
}
