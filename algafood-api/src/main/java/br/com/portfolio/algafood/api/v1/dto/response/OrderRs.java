package br.com.portfolio.algafood.api.v1.dto.response;

import br.com.portfolio.algafood.api.v1.dto.View;
import br.com.portfolio.algafood.domain.entity.Order;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonView;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@JsonFilter("orderFilter")
public class OrderRs {

    @JsonView(View.Basic.class) private String code;
    @JsonView(View.Basic.class) private BigDecimal subtotal;
    @JsonView(View.Basic.class) private BigDecimal taxFreight;
    @JsonView(View.Basic.class) private BigDecimal totalValue;
    private PaymentMethodRs method;
    @JsonView(View.Basic.class) private RestaurantRs restaurant;
    @JsonView(View.Basic.class) private UserRs client;
    private AddressRs addressDelivery;
    private List<OrderItemRs> ordersItens;
    private Set<OrderStatusRs> orderStatus;

    public OrderRs(Order order) {
        this.code = order.getCode();
        this.subtotal = order.getSubtotal();
        this.taxFreight = order.getTaxFreight();
        this.totalValue = order.getTotalValue();
        this.method = new PaymentMethodRs(order.getMethod());
        this.restaurant = new RestaurantRs(order.getRestaurant());
        this.client = new UserRs(order.getClient());
        this.addressDelivery = new AddressRs(order.getAddressDelivery());
        this.ordersItens = order.getOrdersItens().stream().map(OrderItemRs::new).collect(Collectors.toList());
        this.orderStatus = order.getOrderStatus().stream().map(OrderStatusRs::new).collect(Collectors.toSet());
    }
    public String getCode() { return code; }
    public BigDecimal getSubtotal() { return subtotal; }
    public BigDecimal getTaxFreight() { return taxFreight; }
    public BigDecimal getTotalValue() { return totalValue; }
    public PaymentMethodRs getMethod() { return method; }
    public RestaurantRs getRestaurant() { return restaurant; }
    public UserRs getClient() { return client; }
    public AddressRs getAddressDelivery() { return addressDelivery; }
    public List<OrderItemRs> getOrdersItens() { return ordersItens; }
    public Set<OrderStatusRs> getOrderStatus() { return orderStatus; }
}
