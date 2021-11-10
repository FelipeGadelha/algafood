package br.com.portfolio.algafood.api.v1.dto.response;

import br.com.portfolio.algafood.api.v1.dto.View;
import br.com.portfolio.algafood.domain.entity.Restaurant;
import com.fasterxml.jackson.annotation.JsonView;

import java.math.BigDecimal;

public class RestaurantRs {

    @JsonView({View.Basic.class, View.Detail.class})
    private final Long id;
    @JsonView({View.Basic.class, View.Detail.class})
    private final String name;
    @JsonView({View.Basic.class, View.Detail.class})
    private final BigDecimal taxFreight;
    @JsonView(View.Detail.class)
    private final KitchenRs kitchen;
    @JsonView(View.Detail.class)
    private final Boolean active;
    @JsonView(View.Detail.class)
    private final AddressRs address;

    public RestaurantRs(Restaurant restaurant) {
        this.id = restaurant.getId();
        this.name = restaurant.getName();
        this.taxFreight = restaurant.getTaxFreight();
        this.kitchen = new KitchenRs(restaurant.getKitchen());
        this.active = restaurant.getActive();
        this.address = new AddressRs(restaurant.getAddress());
    }

    public Long getId() { return id; }
    public String getName() { return name; }
    public BigDecimal getTaxFreight() { return taxFreight; }
    public KitchenRs getKitchen() { return kitchen; }
}
