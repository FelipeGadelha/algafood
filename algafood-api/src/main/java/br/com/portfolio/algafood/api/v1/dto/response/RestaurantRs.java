package br.com.portfolio.algafood.api.v1.dto.response;

import br.com.portfolio.algafood.api.v1.dto.View;
import br.com.portfolio.algafood.domain.model.Restaurant;

import com.fasterxml.jackson.annotation.JsonView;
import java.math.BigDecimal;
import org.springframework.hateoas.RepresentationModel;

public class RestaurantRs extends RepresentationModel<RestaurantRs> {

    @JsonView({View.Basic.class, View.Detail.class})
    private final Long id;
    @JsonView({View.Basic.class, View.Detail.class})
    private final String name;
    @JsonView(View.Detail.class)
    private final BigDecimal taxFreight;
    @JsonView(View.Detail.class)
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
