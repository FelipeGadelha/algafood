package br.com.portfolio.algafood.api.v1.dto.response;

import br.com.portfolio.algafood.api.v1.dto.View;
import br.com.portfolio.algafood.domain.entity.Product;
import com.fasterxml.jackson.annotation.JsonView;

import java.math.BigDecimal;

public class ProductRs {

    @JsonView({View.Basic.class, View.Detail.class})
    private final Long id;
    @JsonView({View.Basic.class, View.Detail.class})
    private final String name;
    @JsonView(View.Detail.class)
    private final String description;
    @JsonView({View.Basic.class, View.Detail.class})
    private final BigDecimal price;
    @JsonView(View.Detail.class)
    private final Boolean active;

    public ProductRs(Product product) {
        this.id = product.getId();
        this.name = product.getName();
        this.description = product.getDescription();
        this.price = product.getPrice();
        this.active = product.getActive();
    }
    public Long getId() { return id; }
    public String getName() { return name; }
    public String getDescription() { return description; }
    public BigDecimal getPrice() { return price; }
    public Boolean getActive() { return active; }
}
