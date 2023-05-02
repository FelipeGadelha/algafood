package br.com.portfolio.algafood.api.v1.dto.response;

import br.com.portfolio.algafood.domain.model.Kitchen;

public class KitchenRs {

    private final Long id;
    private final String name;

    public KitchenRs(Kitchen kitchen) {
        this.id = kitchen.getId();
        this.name = kitchen.getName();
    }
    public Long getId() { return id; }
    public String getName() { return name; }
}
