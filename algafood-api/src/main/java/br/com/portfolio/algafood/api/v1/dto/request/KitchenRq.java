package br.com.portfolio.algafood.api.v1.dto.request;

import br.com.portfolio.algafood.domain.entity.Kitchen;

import javax.validation.constraints.NotBlank;

public class KitchenRq {

    @NotBlank
    private final String name;

    public KitchenRq(String name) {
        this.name = name;
    }

    public Kitchen convert() {
        return new Kitchen(null, name);
    }
}
