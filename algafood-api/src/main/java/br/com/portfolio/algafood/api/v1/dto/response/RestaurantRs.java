package br.com.portfolio.algafood.api.v1.dto.response;

import br.com.portfolio.algafood.domain.entity.Restaurant;

import java.math.BigDecimal;

public class RestaurantRs {

    private final Long id;
    private final String name;
    private final BigDecimal taxFreight;
    private final Boolean open;

    public RestaurantRs(Restaurant restaurant) {
        this.id = restaurant.getId();
        this.name = restaurant.getName();
        this.taxFreight = restaurant.getTaxFreight();
        this.open = restaurant.getOpen();
    }
    public Long getId() { return id; }
    public String getName() { return name; }
    public BigDecimal getTaxFreight() { return taxFreight; }
    public Boolean getOpen() { return open; }
}
