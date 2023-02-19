package br.com.portfolio.algafood.api.v1.dto.response;

import br.com.portfolio.algafood.api.v1.dto.View;
import br.com.portfolio.algafood.domain.entity.Restaurant;
import com.fasterxml.jackson.annotation.JsonView;

import java.math.BigDecimal;

public class RestaurantDetailRs {

    @JsonView(View.Basic.class)
    private final Long id;
    @JsonView(View.Basic.class)
    private final String name;
    @JsonView(View.Basic.class)
    private final BigDecimal taxFreight;
    private final KitchenRs kitchen;
    private final Boolean active;
    @JsonView(View.Basic.class)
    private final Boolean open;
    private final AddressRs address;

    public RestaurantDetailRs(Restaurant restaurant) {
        this.id = restaurant.getId();
        this.name = restaurant.getName();
        this.taxFreight = restaurant.getTaxFreight();
        this.kitchen = new KitchenRs(restaurant.getKitchen());
        this.active = restaurant.getActive();
        this.open = restaurant.getOpen();
        this.address = new AddressRs(restaurant.getAddress());
    }
    public Long getId() { return id; }
    public String getName() { return name; }
    public BigDecimal getTaxFreight() { return taxFreight; }
    public KitchenRs getKitchen() { return kitchen; }
    public Boolean getActive() { return active; }
    public Boolean getOpen() { return open; }
    public AddressRs getAddress() { return address; }
}
