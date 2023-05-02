package br.com.portfolio.algafood.api.v1.dto.response;

import br.com.portfolio.algafood.api.v1.dto.View;
import br.com.portfolio.algafood.domain.model.Product;
import com.fasterxml.jackson.annotation.JsonView;

import java.math.BigDecimal;

public class ProductRs {

    @JsonView(View.Basic.class) private final Long id;
    @JsonView(View.Basic.class) private final String name;
    private final String description;
    @JsonView(View.Basic.class) private final BigDecimal price;
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
