package br.com.portfolio.algafood.api.v1.dto.response;

import br.com.portfolio.algafood.api.v1.dto.View;
import br.com.portfolio.algafood.domain.entity.Kitchen;
import com.fasterxml.jackson.annotation.JsonView;

public class KitchenRs {

    @JsonView({View.Basic.class, View.Detail.class})
    private final Long id;
    @JsonView({View.Basic.class, View.Detail.class})
    private final String name;

    public KitchenRs(Kitchen kitchen) {
        this.id = kitchen.getId();
        this.name = kitchen.getName();
    }
    public Long getId() { return id; }
    public String getName() { return name; }
}
