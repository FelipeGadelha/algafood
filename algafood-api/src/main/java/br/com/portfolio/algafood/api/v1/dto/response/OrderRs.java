package br.com.portfolio.algafood.api.v1.dto.response;

import br.com.portfolio.algafood.api.v1.dto.View;
import br.com.portfolio.algafood.domain.model.Order;

import com.fasterxml.jackson.annotation.JsonView;
import java.math.BigDecimal;
import org.springframework.hateoas.RepresentationModel;

//@JsonFilter("orderFilter")
public class OrderRs extends RepresentationModel<OrderRs> {

    private String code;
    private BigDecimal subtotal;
    private BigDecimal taxFreight;
    private BigDecimal totalValue;
    private RestaurantRs restaurant;
    private UserRs client;
//    private String nameClient;

    public OrderRs(Order order) {
        this.code = order.getCode();
        this.subtotal = order.getSubtotal();
        this.taxFreight = order.getTaxFreight();
        this.totalValue = order.getTotalValue();
        this.restaurant = new RestaurantRs(order.getRestaurant());
//        this.nameClient = order.getClient().getName();
        this.client = new UserRs(order.getClient());
    }
    public String getCode() { return code; }
    public BigDecimal getSubtotal() { return subtotal; }
    public BigDecimal getTaxFreight() { return taxFreight; }
    public BigDecimal getTotalValue() { return totalValue; }
    public RestaurantRs getRestaurant() { return restaurant; }
    public UserRs getClient() { return client; }
//    public String getNameClient() { return nameClient; }
}
